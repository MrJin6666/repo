package com.goboosoft.company.companyinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.GenerateOrderUtil;
import com.goboosoft.company.companyinspection.dao.ConmpanyInspectDao;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.entity.ConmpanyInspectEntity;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.industry.homepage.dto.InspectDetailsDTO;
import com.goboosoft.industry.multiple.dao.SysCompanyDao;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.enums.SuperAdminEnum;
import com.goboosoft.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Service
public class ConmpanyInspectServiceImpl extends CrudServiceImpl<ConmpanyInspectDao, ConmpanyInspectEntity, ConmpanyInspectDTO> implements ConmpanyInspectService {

    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CompanyManageLogService companyManageLogService;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Autowired
    private GovernPlanService governPlanService;
    @Autowired
    private ConmpanyInspectDao ConmpanyInspectDao;
    @Override
    public QueryWrapper<ConmpanyInspectEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<ConmpanyInspectEntity> wrapper = new QueryWrapper<>();
        Long id = Long.valueOf((String)params.get("id"));
        Long userId = Long.valueOf((Long)params.get("userId"));
        if(params.get("companyId")!=null){
            wrapper.eq("company_id",Long.valueOf((Long)params.get("companyId")));
        }else{
            wrapper.eq("user_id", userId);
        }
        wrapper.eq("list_id", id);
        return wrapper;
    }

    @Override
    public List<ConmpanyInspectEntity> selectConmpanyInspectList(QueryWrapper wrapper){
        List<ConmpanyInspectEntity> list = baseDao.selectList(wrapper);
        return list;
    }

    @Override
    public List<ConmpanyInspectDTO> getListNameAndNum(Long userId){
        return baseDao.getListNameAndNum(userId);
    }

    @Override
    @Transactional
    public Long saveCompany(SysCompanyDTO sysCompanyDTO,Long userId){

        SysCompanyEntity sysCompanyEntity = ConvertUtils.sourceToTarget(sysCompanyDTO, SysCompanyEntity.class);
        sysCompanyEntity.setXgdw(sysCompanyDTO.getXgdw());
        sysCompanyService.insert(sysCompanyEntity);
        SysUserDTO sysUserDTO = sysUserService.get(userId);
        sysUserDTO.setCompanyId(sysCompanyEntity.getId());
        sysUserDTO.setSuperAdmin(SuperAdminEnum.YES.value());
        sysUserService.updateCompanyId(sysCompanyEntity.getId(),userId);
        baseDao.updateCompanyId(sysCompanyEntity.getId(),userId);
        CompanyManageLogDTO companyManageLogDTO = new CompanyManageLogDTO();
        companyManageLogDTO.setCompanyId(sysCompanyEntity.getId());
        companyManageLogDTO.setCause("提交企业申请");
        companyManageLogService.save(companyManageLogDTO);
        return sysCompanyEntity.getId();
    }

    @Override
    public Integer getCount(Long listId,Long companyId,Long userId){
        return baseDao.getCount(listId,companyId,userId);
    }
    /**
     * dmxuan
     * 生成随机用户名，数字和字母组成,
     * @Date 2019/1/18 11:34
     * @Param []
     * @return java.lang.String
     **/
    @Override
    public String getRoomId() {
        String roomId = "";
        Random random = new Random();
//	    生成6位
        for (int i = 0; i < 10; i++) {
            roomId += String.valueOf(random.nextInt(10));
        }
        return roomId;
    }

    /**
     * 根据企业id和检查项id查询检查项个数
     */
    @Override
    public List<ConmpanyInspectDTO> getInspectCountById(Long companyId ,Long listId){
        List<ConmpanyInspectDTO> inspectNum =ConmpanyInspectDao.getInspectCountById(companyId,listId);
        return inspectNum;
    }
    /**
     * 检查项分组总数统计
     */
    @Override
    public List<ConmpanyInspectDTO> getInspectCountByGroup( Long userId){
       List<ConmpanyInspectDTO>  list= ConmpanyInspectDao.getInspectCountByGroup(userId);
       return list;
    }
    /**
     * 根据检查项id获取检查项统计详情列表
     */
    @Override
   public List<InspectDetailsDTO> getInspectCountDetails(Map<String,Object> params){
        return ConmpanyInspectDao.getInspectCountDetails(params);
    }

    @Override
    public Integer getByName(String name){
        return baseDao.getByName(name);
    }

    @Override
    public Integer isExits(ConmpanyInspectDTO dto){
        return baseDao.isExits(dto);
    }

    /**
     * 生成治理计划
     * @param list
     * @param companyId
     */
    @Override
    @Transactional
    public void generateGovern(List<BasicInspectionListDTO> list, Long companyId){
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