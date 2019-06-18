package com.goboosoft.company.govern.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.govern.dto.GovernPlanDTO;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;

import java.util.List;
import java.util.Map;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
public interface GovernPlanService extends CrudService<GovernPlanEntity, GovernPlanDTO> {

    List<GovernPlanDTO> getGovernPlan(Long userId, Map<String,Object> params);

    GovernPlanDTO getGorvenPlanById(Long id);

    List<GovernPlanDTO> getGovernLists(Map<String, Object> params);

    List<GovernPlanDTO> getGovernListsByFile(Map<String, Object> params);

    /**
     * 获取未生成计划的列表
     * @param governPlanMap
     * @return
     */
    List<BasicInspectionListDTO> getNoProducePlan(Map<String, Object> governPlanMap);
}