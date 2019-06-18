package com.goboosoft.modules.governplan.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.governplan.dto.GovernPlanDTO;
import com.goboosoft.modules.governplan.entity.GovernPlanEntity;
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

    IPage<GovernPlanDTO> findPageList(IPage page,@Param("params") Map<String, Object> params);

    IPage<Map<String, Object>> screenCompanyPlan(IPage page, Map<String, Object> params);

    List<BasicInspectionListDTO> getNoProducePlan(@Param("params")Map<String, Object> params);
}