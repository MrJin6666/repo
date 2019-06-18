package com.goboosoft.company.govern.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.govern.dao.GovernPlanDao;
import com.goboosoft.company.govern.dto.GovernPlanDTO;
import com.goboosoft.company.govern.dto.GovernProcessPlanDTO;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.company.govern.entity.GovernProcessPlanEntity;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.company.govern.service.GovernProcessPlanService;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.entity.BasicInspectionListEntity;
import com.goboosoft.industry.multiple.service.InspectionListService;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.log.dto.SysLogLoginDTO;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Service
public class GovernPlanServiceImpl extends CrudServiceImpl<GovernPlanDao, GovernPlanEntity, GovernPlanDTO> implements GovernPlanService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private InspectionListService inspectionListService;
    @Autowired
    private GovernProcessPlanService governProcessPlanService;

    @Autowired
    private SysCompanyService sysCompanyService;
    @Override
    public QueryWrapper<GovernPlanEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GovernPlanEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<GovernPlanDTO> getGovernPlan(Long userId,Map<String,Object> params){
        SysUserDTO sysUserDTO = sysUserService.get(userId);
        params.put("companyId",sysUserDTO.getCompanyId());
        List<GovernPlanDTO> governPlan = baseDao.getGovernPlan(params);
        /*if(!"".equals(status)) {
            if ("1".equals(status)) {
                map.put("status", "2");
                List<GovernPlanDTO> governPlan1 = baseDao.getGovernPlan(map);
                for (GovernPlanDTO governPlanDTO : governPlan1) {
                    governPlan.add(governPlanDTO);
                }
            }
        }*/
        return governPlan;
    }

    @Override
    public GovernPlanDTO getGorvenPlanById(Long id){
        QueryWrapper<GovernPlanEntity> wrapper = new QueryWrapper<GovernPlanEntity>();
        wrapper.eq("id",id);
        GovernPlanEntity governPlanEntity = baseDao.selectOne(wrapper);
        Long inspectionListId = governPlanEntity.getInspectionListId();
        BasicInspectionListDTO basicInspectionListDTO = inspectionListService.get(inspectionListId);
        SysCompanyDTO sysCompanyDTO = sysCompanyService.get(governPlanEntity.getCompanyId());
        GovernPlanDTO governPlanDTO = ConvertUtils.sourceToTarget(governPlanEntity, GovernPlanDTO.class);
        governPlanDTO.setCompanyName(sysCompanyDTO.getName());
        governPlanDTO.setGovernItemName(basicInspectionListDTO.getName());

        if("1".equals(governPlanDTO.getPlanStatus())){
            QueryWrapper<GovernProcessPlanEntity> governProcessPlanEntityQueryWrapper = new QueryWrapper<GovernProcessPlanEntity>();
            governProcessPlanEntityQueryWrapper.eq("govern_plan_id",id);
            GovernProcessPlanEntity governProcessPlanEntity = governProcessPlanService.selectLists(governProcessPlanEntityQueryWrapper);
            GovernProcessPlanDTO governProcessPlanDTO = ConvertUtils.sourceToTarget(governProcessPlanEntity, GovernProcessPlanDTO.class);
            governPlanDTO.setGovernProcessPlanDTO(governProcessPlanDTO);
        }
        return governPlanDTO;
    }

    @Override
    public List<GovernPlanDTO> getGovernLists(Map<String, Object> params){
        SysUserDTO sysUserDTO = sysUserService.get((Long)params.get("userId"));
        Long companyId;
        if(params.get("companyId")==null){
            companyId = sysUserDTO.getCompanyId();
            params.put("companyId",companyId);
        }
        List<GovernPlanDTO> governListsCount = baseDao.getGovernListsCount(params);
        return governListsCount;
    }

    /**
     * 查询治理计划的治理过程的最后的时间
     * @param params
     * @return
     */
    @Override
    public List<GovernPlanDTO> getGovernListsByFile(Map<String, Object> params){
        Long companyId;
        if(params.get("companyId")==null) {
            companyId = SecurityUser.getUser().getCompanyId();
            params.put("companyId",companyId);
        }
        List<GovernPlanDTO> list = baseDao.getGovernListsByFile(params);
        return list;
    }

    @Override
    public List<BasicInspectionListDTO> getNoProducePlan(Map<String, Object> params){
        return baseDao.getNoProducePlan(params);
    }
}