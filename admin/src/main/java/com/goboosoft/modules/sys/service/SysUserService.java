/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.sys.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.BaseService;
import com.goboosoft.modules.sys.entity.SysUserEntity;
import com.goboosoft.modules.sys.dto.SysUserDTO;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDTO> page(Map<String, Object> params);

	List<SysUserDTO> list(Map<String, Object> params);

	SysUserDTO get(Long id);

	SysUserDTO getByUsername(String username);

	void save(SysUserDTO dto);

	void update(SysUserDTO dto);

	void updateDTO(SysUserDTO dto);

	void delete(Long[] ids);

	SysUserDTO getCompanyId(Long companyId);

	/**
	 * 修改密码
	 * @param id           用户ID
	 * @param newPassword  新密码
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);

	List<SysUserDTO> findUserChildren(Long deptId);

    List<SysUserDTO> findDeptUserChildren(Long deptId);
	/**
	 * 通过部门id选择负责人
	 * @param deptId
	 * @return
	 */
	List<SysUserDTO> getUserDeptName(String deptId);

    void addUserSave(SysUserDTO dto);
}
