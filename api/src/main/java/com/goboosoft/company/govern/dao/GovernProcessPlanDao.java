package com.goboosoft.company.govern.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.govern.entity.GovernProcessPlanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Mapper
public interface GovernProcessPlanDao extends BaseDao<GovernProcessPlanEntity> {

    List<GovernProcessPlanEntity> selectListByProcess(@Param("id") Long id);
}