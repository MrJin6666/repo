package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;

import java.util.List;

/**
 * created by yangkun
 * created time 2019/2/11
 * class describe
 */
public interface CompanyService extends BaseService<SysCompanyEntity> {

    /**
     * 查询 - 通过公司类型
     * @param companyType
     * @return
     */
    List<SysCompanyEntity> getCompanyListByCompanyType(String companyType);
}
