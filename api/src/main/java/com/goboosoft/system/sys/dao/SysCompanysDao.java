package com.goboosoft.system.sys.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.system.sys.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础 - 公司信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-18
 */
@Mapper
public interface SysCompanysDao extends BaseDao<SysCompanyEntity> {
	
}