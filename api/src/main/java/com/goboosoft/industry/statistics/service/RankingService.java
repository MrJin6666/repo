package com.goboosoft.industry.statistics.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.statistics.dto.RankingDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;

import java.util.List;
import java.util.Map;
/**
 * 统计-排名
 *
 * @author jinxin
 */
public interface RankingService extends BaseService<BusinInspectionTaskEntity> {
    List<RankingDTO> queryRanking(Map<String,Object> params);
}
