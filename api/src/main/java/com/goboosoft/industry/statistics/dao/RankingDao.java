package com.goboosoft.industry.statistics.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.statistics.dto.RankingDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 统计-排名
 *
 * @author jinxin
 */
@Mapper
@Repository
public interface RankingDao extends BaseDao<BusinInspectionTaskEntity> {
    List<RankingDTO> queryRanking( @Param("params") Map<String,Object> params);
}
