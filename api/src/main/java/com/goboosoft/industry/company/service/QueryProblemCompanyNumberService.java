package com.goboosoft.industry.company.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.company.dto.QueryProblemCompanyNumberDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
/**
 * 获取问题企业总数（带权限）
 *
 * @author jinxin
 */
public interface QueryProblemCompanyNumberService extends BaseService<SysCompanyEntity> {
    QueryProblemCompanyNumberDTO queryNumber();
}
