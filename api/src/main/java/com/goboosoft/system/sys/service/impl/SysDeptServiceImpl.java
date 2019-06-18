/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.exception.ErrorCode;
import com.goboosoft.common.exception.RenException;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.TreeUtils;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dao.SysDeptDao;
import com.goboosoft.system.sys.dto.SysDeptAllPersionDTO;
import com.goboosoft.system.sys.dto.SysDeptDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysDeptEntity;
import com.goboosoft.system.sys.enums.SuperAdminEnum;
import com.goboosoft.system.sys.service.SysDeptService;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
	@Autowired
	private SysUserService sysUserService;

	@Override
	public List<SysDeptDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", getSubDeptIdList(user.getDeptId()));
		}

		//查询部门列表
		List<SysDeptEntity> entityList = baseDao.getList(params);

		List<SysDeptDTO> dtoList = ConvertUtils.sourceToTarget(entityList, SysDeptDTO.class);

		return TreeUtils.build(dtoList);
	}

	@Override
	public SysDeptDTO get(Long id) {
		//超级管理员，部门ID为null
		if(id == null){
			return null;
		}

		SysDeptEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysDeptDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysDeptDTO dto) {
		SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

		entity.setPids(getPidList(entity.getPid()));
		insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysDeptDTO dto) {
		SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

		//上级部门不能为自身
		if(entity.getId().equals(entity.getPid())){
			throw new RenException(ErrorCode.SUPERIOR_DEPT_ERROR);
		}

		entity.setPids(getPidList(entity.getPid()));
		updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		//判断是否有子部门
		List<Long> subList = getSubDeptIdList(id);
		if(subList.size() > 1){
			throw new RenException(ErrorCode.DEPT_SUB_DELETE_ERROR);
		}

		//判断部门下面是否有用户
		int count = sysUserService.getCountByDeptId(id);
		if(count > 0){
			throw new RenException(ErrorCode.DEPT_USER_DELETE_ERROR);
		}

		//删除
		baseDao.deleteById(id);
	}

	@Override
	public List<Long> getSubDeptIdList(Long id) {
		List<Long> deptIdList = baseDao.getSubDeptIdList("%" + id + "%");
		deptIdList.add(id);

		return deptIdList;
	}

	/**
	 * 获取所有上级部门ID
	 * @param pid 上级ID
	 */
	private String getPidList(Long pid){
		//顶级部门，无上级部门
		if(Constant.DEPT_ROOT.equals(pid)){
			return Constant.DEPT_ROOT + "";
		}

		//所有部门的id、pid列表
		List<SysDeptEntity> deptList = baseDao.getIdAndPidList();

		//list转map
		Map<Long, SysDeptEntity> map = new HashMap<>(deptList.size());
		for(SysDeptEntity entity : deptList){
			map.put(entity.getId(), entity);
		}

		//递归查询所有上级部门ID列表
		List<Long> pidList = new ArrayList<>();
		getPidTree(pid, map, pidList);

		return StringUtils.join(pidList, ",");
	}

	private void getPidTree(Long pid, Map<Long, SysDeptEntity> map, List<Long> pidList) {
		//顶级部门，无上级部门
		if(Constant.DEPT_ROOT.equals(pid)){
			return ;
		}

		//上级部门存在
		SysDeptEntity parent = map.get(pid);
		if(parent != null){
			getPidTree(parent.getPid(), map, pidList);
		}

		pidList.add(pid);
	}
	@Override
	public List<SysDeptDTO> getDeptList(Long pid){
		List<SysDeptDTO> list = new ArrayList<SysDeptDTO>();
		QueryWrapper<SysDeptEntity> wrapper = new QueryWrapper<SysDeptEntity>();
		wrapper.eq("is_com",0);
		wrapper.eq("pid",pid);
		List<SysDeptEntity> sysDeptEntities = baseDao.selectList(wrapper);
		for(SysDeptEntity sysDeptEntity : sysDeptEntities){
			SysDeptDTO sysDeptDTO = ConvertUtils.sourceToTarget(sysDeptEntity, SysDeptDTO.class);
			SysUserDTO sysUserDTO = sysUserService.get(sysDeptEntity.getUserId());
			sysDeptDTO.setSysUserDTO(sysUserDTO);
			list.add(sysDeptDTO);
		}
		return list;
	}

	@Override
	public List<SysDeptAllPersionDTO> getDeptAllPersion(Long userId) {
		return baseDao.getDeptAllPersion(userId);
	}
}
