package com.goboosoft.modules.syscompany.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.GenerateOrderUtil;
import com.goboosoft.common.utils.PushUtil;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.basicinspectionlist.service.BasicInspectionListService;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.modules.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.modules.companymenuuser.dto.CompanyMenuDTO;
import com.goboosoft.modules.companymenuuser.entity.CompanyUserMenuEntity;
import com.goboosoft.modules.companymenuuser.service.CompanyMenuService;
import com.goboosoft.modules.companymenuuser.service.CompanyUserMenuService;
import com.goboosoft.modules.conmpanyinspect.service.ConmpanyInspectService;
import com.goboosoft.modules.governplan.entity.GovernPlanEntity;
import com.goboosoft.modules.governplan.service.GovernPlanService;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.enums.SuperAdminEnum;
import com.goboosoft.modules.sys.service.SysUserService;
import com.goboosoft.modules.syscompany.dao.SysCompanyDao;
import com.goboosoft.modules.syscompany.dto.SysCompanyDTO;
import com.goboosoft.modules.syscompany.entity.SysCompanyEntity;
import com.goboosoft.modules.syscompany.service.SysCompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Service
public class SysCompanyServiceImpl extends CrudServiceImpl<SysCompanyDao, SysCompanyEntity, SysCompanyDTO> implements SysCompanyService {

    @Autowired
    private CompanyManageLogService companyManageLogService;
    @Autowired
    private BasicInspectionListService basicInspectionListService;
    @Autowired
    private GovernPlanService governPlanService;
    @Autowired
    private CompanyUserMenuService companyUserMenuService;
    @Autowired
    private CompanyMenuService companyMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PushUtil pushUtil;

    @Override
    public QueryWrapper<SysCompanyEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SysCompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<SysCompanyDTO> pageList(Map<String, Object> params) {
        IPage<SysCompanyDTO> pageList = baseDao.findPageList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<SysCompanyDTO> objectPageData = new PageData<SysCompanyDTO>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void audit(SysCompanyDTO dto) {
        //修改审核
        SysCompanyEntity com = baseDao.selectById(dto.getId());
        com.setCause(dto.getCause());
        CompanyManageLogDTO companyManageLogDTO = new CompanyManageLogDTO();
        companyManageLogDTO.setAuditUser(SecurityUser.getUserId());
        companyManageLogDTO.setResult(dto.getStatus());
        companyManageLogDTO.setType("1");
        companyManageLogDTO.setCompanyId(dto.getId());
        if(dto.getPrincipalName()!=null) {
            Long principalName =Long.valueOf(dto.getPrincipalName());
            com.setUserId(Long.valueOf(principalName));
        }
        if(dto.getStatus().equals(String.valueOf(Constant.CompanyStatusService.PASS.value()))){ // 通过审核
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("companyId",dto.getId());
            map.put("planStatus",Constant.PlanstatusService.grovern.value());
            //获取未生成的治理项
            //List<BasicInspectionListDTO> noProducePlan = governPlanService.getNoProducePlan(map);

            // 计划的发放
            // 查询当前企业所有的内容模块所关联的计划条目
            com.setCause(null);
            List<BasicInspectionListDTO> list = basicInspectionListService.findConmpanyInspectList(dto.getId());

            //判断此企业之前是否有生成治理计划
            ///if(noProducePlan != null){
                //generateGovern(noProducePlan,dto.getId());
            //}else{
            generateGovern(list,dto.getId());
            //}

            //修改权限
            SysUserDTO sysUserDTO = sysUserService.getCompanyId(dto.getId());
            sysUserDTO.setSuperAdmin(SuperAdminEnum.YES.value());
            sysUserService.update(sysUserDTO);
            Integer count = companyUserMenuService.deleteByUserId(SecurityUser.getUserId());
            List<CompanyMenuDTO> menuDTOList = companyMenuService.selectAll();
            for(CompanyMenuDTO companyMenuDTO : menuDTOList){
                CompanyUserMenuEntity companyUserMenuEntity = new CompanyUserMenuEntity();
                companyUserMenuEntity.setCompanyMenuModule(companyMenuDTO.getModule());
                companyUserMenuEntity.setUserId(sysUserDTO.getId());
                companyUserMenuService.insert(companyUserMenuEntity);
            }
            // 推送
            pushUtil.companyPushMessage(sysUserDTO.getRegistrationId(), PushUtil.PushMsgType.PASS);
        }
        companyManageLogDTO.setCause(dto.getCause());
        companyManageLogService.save(companyManageLogDTO);
        com.setStatus(dto.getStatus());
        baseDao.updateById(com);
    }


    @Override
    public PageData<Map<String,Object>> pageScreenList(Map<String, Object> params) {
        IPage<Map<String,Object>> pageList = baseDao.pageScreenList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<Map<String,Object>> objectPageData = new PageData<Map<String,Object>>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }

    @Override
    public SysCompanyDTO findInspectionNum(Long companyId) {
        return baseDao.findInspectionNum(companyId);
    }

    @Transactional
    public void generateGovern(List<BasicInspectionListDTO> list,Long companyId){
        ArrayList<GovernPlanEntity> governPlanEntities = new ArrayList<>();
        if(list != null && list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                BasicInspectionListDTO basicDTO = list.get(i);
                GovernPlanEntity governPlanEntity = new GovernPlanEntity();
                governPlanEntity.setName(basicDTO.getName());
                // 计算结束时间
                long cycle = basicDTO.getCycle() * 24 * 60 * 60 * 1000;// 天数转化毫秒
                governPlanEntity.setEndDate(new Date(System.currentTimeMillis() + cycle));
                governPlanEntity.setStatus(Constant.GovernService.PENDIND.value());
                governPlanEntity.setCompanyInspectionId(basicDTO.getCompanyInspectId());
                governPlanEntity.setPlanStatus(Constant.PlanstatusService.grovern.value());
                governPlanEntity.setCode("J" + GenerateOrderUtil.doOrderNum());
                governPlanEntity.setInspectionListId(basicDTO.getId());
                governPlanEntity.setCompanyId(companyId);
                governPlanEntities.add(governPlanEntity);
            }
        }
        governPlanService.insertBatch(governPlanEntities);

    }
}