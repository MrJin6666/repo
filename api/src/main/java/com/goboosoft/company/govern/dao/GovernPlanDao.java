package com.goboosoft.company.govern.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.govern.dto.GovernPlanDTO;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Mapper
public interface GovernPlanDao extends BaseDao<GovernPlanEntity> {

    List<GovernPlanDTO> getGovernListsCount(@Param("params") Map<String, Object> params);

    List<GovernPlanDTO> getGovernListsByFile(@Param("params")Map<String, Object> params);

    List<GovernPlanDTO> getGovernPlan(@Param("params") Map<String,Object> params);

    List<BasicInspectionListDTO> getNoProducePlan(@Param("params")Map<String, Object> params);
}