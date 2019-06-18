package com.goboosoft.industry.statistics.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.statistics.dao.RankingDao;
import com.goboosoft.industry.statistics.dto.RankingDTO;
import com.goboosoft.industry.statistics.service.RankingService;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 统计-排名
 *
 * @author jinxin
 */
@Service
public class RankingServiceImpl extends BaseServiceImpl<RankingDao,BusinInspectionTaskEntity> implements RankingService {
    @Autowired
    private RankingDao rankingDao;
    @Override
    public List<RankingDTO> queryRanking(Map<String, Object> params) {
        return rankingDao.queryRanking(params);
    }
}
