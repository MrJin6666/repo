package com.goboosoft.modules.companymanagelog.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.modules.companymanagelog.entity.CompanyManageLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Mapper
public interface CompanyManageLogDao extends BaseDao<CompanyManageLogEntity> {

    List<CompanyManageLogDTO> findByComId(Long comId);

    CompanyManageLogDTO findByCompanyId(Long companyId);
}