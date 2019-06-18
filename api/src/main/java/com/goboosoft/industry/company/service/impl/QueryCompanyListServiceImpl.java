package com.goboosoft.industry.company.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.company.dao.QueryCompanyListDao;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.company.service.QueryCompanyListService;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 获取公司列表（带权限）
 *
 * @author jinxin
 */
@Service
public class QueryCompanyListServiceImpl extends BaseServiceImpl<QueryCompanyListDao, SysCompanyEntity> implements QueryCompanyListService {
    @Autowired
    private QueryCompanyListDao queryCompanyListDao;

    @Override
    public List<QueryCompanyListDTO> queryCompanyList(Map<String, Object> params) {
        return queryCompanyListDao.queryCompanyList(params);
    }

    @Override
    public List<QueryCompanyListDTO> queryCompanyListByStatus(Map<String, Object> params) {
        return queryCompanyListDao.queryCompanyListByStatus(params);
    }

    @Override
    public List<QueryCompanyListDTO> queryCompanyListByCheckStatus(Map<String, Object> params) {
        return queryCompanyListDao.queryCompanyListByCheckStatus(params);
    }

    @Override
    public Long getCompanyTotle(Long userId) {
        return queryCompanyListDao.getCompanyTotle(userId);

    }
    @Override
    public List<QueryCompanyListDTO> queryCompanyListByGovernStatus(Map<String, Object> params) {
        return queryCompanyListDao.queryCompanyListByGovernStatus(params);
    }

}
