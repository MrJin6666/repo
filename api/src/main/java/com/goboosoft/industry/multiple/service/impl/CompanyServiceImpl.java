package com.goboosoft.industry.multiple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.multiple.dao.SysCompanyDao;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.industry.multiple.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公司
 * created by yangkun
 * created time 2019/2/11
 * class describe
 */
@Service("companyService")
@Transactional
public class CompanyServiceImpl extends BaseServiceImpl<SysCompanyDao, SysCompanyEntity> implements CompanyService {

    @Override
    public List<SysCompanyEntity> getCompanyListByCompanyType(String companyType) {
        QueryWrapper<SysCompanyEntity> wr = new QueryWrapper<>();
        wr.eq("company_type", companyType);
        return baseDao.selectList(wr);
    }
}
