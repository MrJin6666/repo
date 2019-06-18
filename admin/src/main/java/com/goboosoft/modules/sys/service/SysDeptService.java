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
import com.goboosoft.modules.sys.dto.OrganUserDTO;
import com.goboosoft.modules.sys.dto.SysDeptDTO;
import com.goboosoft.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

	List<SysDeptDTO> list(Map<String, Object> params);

	SysDeptDTO get(Long id);

	void save(SysDeptDTO dto);

	void update(SysDeptDTO dto);

	void delete(Long id);

	/**
	 * 根据部门ID，获取本部门及子部门ID列表
	 * @param id   部门ID
	 */
	List<Long> getSubDeptIdList(Long id);

    List<SysDeptDTO> findAll();

	SysDeptDTO getByPhone(String username);

	PageData<OrganUserDTO> findOrganList(Map<String, Object> params);

    List<SysDeptDTO> childList(Long deptId,int isDeptHead);

    List<SysDeptDTO> childListDept(Long deptId, int isDeptHead);

    void organSave(SysDeptDTO dto);

	void organUpdate(SysDeptDTO dto);
}