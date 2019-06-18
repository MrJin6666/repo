package com.goboosoft.company.companymanagelog.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.entity.CompanyManageLogEntity;

import java.util.List;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
public interface CompanyManageLogService extends CrudService<CompanyManageLogEntity, CompanyManageLogDTO> {

    List<CompanyManageLogEntity> getLists(Long companyId);
}