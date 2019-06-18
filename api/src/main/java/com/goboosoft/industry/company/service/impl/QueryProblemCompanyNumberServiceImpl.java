package com.goboosoft.industry.company.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.company.dao.QueryProblemCompanyNumberDao;
import com.goboosoft.industry.company.dto.QueryProblemCompanyNumberDTO;
import com.goboosoft.industry.company.service.QueryProblemCompanyNumberService;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.system.security.user.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取问题企业总数（带权限）
 *
 * @author jinxin
 */
@Service
public class QueryProblemCompanyNumberServiceImpl extends BaseServiceImpl<QueryProblemCompanyNumberDao, SysCompanyEntity> implements QueryProblemCompanyNumberService {
    @Autowired
    private QueryProblemCompanyNumberDao queryProblemCompanyNumberDao;

    @Override
    public QueryProblemCompanyNumberDTO queryNumber() {
        Long userId = SecurityUser.getUserId();
        Integer redCardCount = queryProblemCompanyNumberDao.queryRedCard(userId);
        Integer yellowCardCount = queryProblemCompanyNumberDao.queryYellowCard(userId);
        Integer ordinaryCardCount = queryProblemCompanyNumberDao.queryOrdinaryCard(userId);
        QueryProblemCompanyNumberDTO queryProblemCompanyNumberDTO = new QueryProblemCompanyNumberDTO();
        queryProblemCompanyNumberDTO.setRedCardCount(redCardCount);
        queryProblemCompanyNumberDTO.setYellowCardCount(yellowCardCount);
        queryProblemCompanyNumberDTO.setOrdinaryCardCount(ordinaryCardCount);
        return queryProblemCompanyNumberDTO;
    }
}
