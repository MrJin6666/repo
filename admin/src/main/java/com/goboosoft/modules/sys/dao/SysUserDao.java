/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.sys.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getById(Long id);

	SysUserEntity getByUsername(String username);

	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);

	List<SysUserDTO> findUserChildren(Long deptId);

	List<SysUserDTO> findDeptUserChildren(Long deptId);

	SysUserDTO getCompanyId(@Param("companyId") Long companyId);

	List<SysUserDTO> getUserDeptName(@Param("deptId") Long deptId);
}