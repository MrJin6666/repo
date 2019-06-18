package com.goboosoft.modules.governplan.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.governplan.dto.GovernPlanDTO;
import com.goboosoft.modules.governplan.entity.GovernPlanEntity;

import java.util.List;
import java.util.Map;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
public interface GovernPlanService extends CrudService<GovernPlanEntity, GovernPlanDTO> {

    PageData<GovernPlanDTO> pageList(Map<String, Object> params);

    PageData<Map<String, Object>> screenCompanyPlan(Map<String, Object> params);

    /**
     * 获取未生成的治理项
     * @param params
     * @return
     */
    List<BasicInspectionListDTO> getNoProducePlan(Map<String, Object> params);
}