/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.sys.dto.OrganUserDTO;
import com.goboosoft.modules.sys.dto.SysDeptDTO;
import com.goboosoft.modules.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    List<SysDeptEntity> getList(Map<String, Object> params);

    SysDeptEntity getById(Long id);

    /**
     * 获取所有部门的id、pid列表
     */
    List<SysDeptEntity> getIdAndPidList();

    /**
     * 根据部门ID，获取所有子部门ID列表
     * @param id   部门ID
     */
    List<Long> getSubDeptIdList(long id);

    List<SysDeptDTO> findAll();

    SysDeptDTO getByPhone(String username);

    IPage<OrganUserDTO> findOrganList(IPage page, @Param("params") Map<String, Object> params);

    List<SysDeptDTO> childList(@Param("deptId") Long deptId,@Param("isDeptHead") int isDeptHead);

    List<SysDeptDTO> childListDept(@Param("deptId") Long deptId,@Param("isDeptHead") int isDeptHead);


}