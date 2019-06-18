package com.goboosoft.modules.companymanagelog.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.modules.companymanagelog.entity.CompanyManageLogEntity;

import java.util.List;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
public interface CompanyManageLogService extends CrudService<CompanyManageLogEntity, CompanyManageLogDTO> {

    List<CompanyManageLogDTO> findByComId(Long id);
    CompanyManageLogDTO findByCompanyId(Long companyId);
}