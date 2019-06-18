package com.goboosoft.company.govern.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.govern.dto.GovernProcessPlanDTO;
import com.goboosoft.company.govern.entity.GovernProcessPlanEntity;

import java.util.List;
import java.util.Map;

/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
public interface GovernProcessPlanService extends CrudService<GovernProcessPlanEntity, GovernProcessPlanDTO> {

    Integer addGorvenPlanPicture(Long planId);

    Map<String,Object> getProcessPlan(Long id);

    GovernProcessPlanEntity selectLists(QueryWrapper wrapper);
}