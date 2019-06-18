/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
@Repository
public interface SysUserDao extends BaseDao<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getById(Long id);

	SysUserEntity getByUsername(String username);

	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);

    SysUserDTO getUser(@Param("userId") Long userId);

    Integer updateCompanyId(@Param("companyId")Long companyId, @Param("userId")Long userId);

    SysUserDTO getCompanyId(@Param("companyId") Long companyId);

	Long selectByMobile(@Param("mobile")String mobile);

    List<SysUserDTO> getUserDeptName(@Param("deptId") Long deptId);

    Integer updateByUserId(@Param("userId") Long userId);

    List<SysUserDTO> getUserCompany(@Param("companyId") Long companyId);

	List<SysUserDTO> getUserDept(@Param("deptId") Long deptId);

    void updateHeadurlAndUserName(@Param("params") Map<String, String> map, Long userId);

    void updateSuperAdmin(@Param("userId") Long userId, @Param("superAdmin") int superAdmin);

    Integer updateRegistrationId(@Param("params") Map<String, Object> map);
}