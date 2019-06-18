package com.goboosoft.industry.supervisionandinspection.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.statistics.dto.CommonProblemsDTO;
import com.goboosoft.industry.supervisionandinspection.dto.InspectionContentDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemContentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 业务 - 督导检查任务项内容信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Mapper
public interface BusinInspectionTaskItemContentDao extends BaseDao<BusinInspectionTaskItemContentEntity> {
    /**
     * 常见问题统计
     * @param params
     * @return
     */
    List<CommonProblemsDTO> getProblemsList(@Param("params") Map<String, Object> params);
}