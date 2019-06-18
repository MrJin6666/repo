package com.goboosoft.company.companymenuuser.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.companymenuuser.entity.CompanyUserMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Mapper
public interface CompanyUserMenuDao extends BaseDao<CompanyUserMenuEntity> {
	
}