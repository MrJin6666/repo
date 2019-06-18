package com.goboosoft.company.companymanagelog.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.companymanagelog.entity.CompanyManageLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Mapper
public interface CompanyManageLogDao extends BaseDao<CompanyManageLogEntity> {
	
}