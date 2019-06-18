/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.exception.ErrorCode;
import com.goboosoft.common.exception.RenException;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.TreeUtils;
import com.goboosoft.modules.message.service.SysSmsService;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.security.user.UserDetail;
import com.goboosoft.modules.sys.dao.SysDeptDao;
import com.goboosoft.modules.sys.dto.OrganUserDTO;
import com.goboosoft.modules.sys.dto.SysDeptDTO;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.entity.SysDeptEntity;
import com.goboosoft.modules.sys.entity.SysRoleUserEntity;
import com.goboosoft.modules.sys.entity.SysUserEntity;
import com.goboosoft.modules.sys.enums.SuperAdminEnum;
import com.goboosoft.modules.sys.service.SysDeptService;
import com.goboosoft.modules.sys.service.SysRoleUserService;
import com.goboosoft.modules.sys.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysSmsService sysSmsService;
	@Autowired
	private SysRoleUserService sysRoleUserService;
	@Value("${SMS.signName}")
	private String signName;
	@Value("${SMS.template}")
	private String template;

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
		List<Long> deptIdList = baseDao.getSubDeptIdList(id);
		deptIdList.add(id);

		return deptIdList;
	}

	@Override
	public List<SysDeptDTO> findAll() {
		return baseDao.findAll();
	}

	@Override
	public SysDeptDTO getByPhone(String username) {
		return baseDao.getByPhone(username);
	}

	@Override
	public PageData<OrganUserDTO> findOrganList(Map<String, Object> params) {
		IPage<OrganUserDTO> pageList = baseDao.findOrganList(getPage(params, Constant.CREATE_DATE, false),params);
		PageData<OrganUserDTO> objectPageData = new PageData<OrganUserDTO>(pageList.getRecords(), pageList.getTotal());
		return objectPageData;
	}

	@Override
	public List<SysDeptDTO> childList(Long deptId,int isDeptHead) {
		return baseDao.childList(deptId,isDeptHead);
	}

	@Override
	public List<SysDeptDTO> childListDept(Long deptId, int isDeptHead) {
		return baseDao.childListDept(deptId,isDeptHead);
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
	@Transactional
	public void organSave(SysDeptDTO dto) {
		long userId = 0L;
		//效验数据
		//ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
		SysDeptEntity sysDeptEntity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);
		// 查询上级的pids
		SysDeptDTO sysDeptDTO = this.get(dto.getPid());
		this.insert(sysDeptEntity);
		sysDeptEntity.setPids(sysDeptDTO.getPids()+","+sysDeptEntity.getId());
		this.updateById(sysDeptEntity);
		if(dto.getIsCom() == 0 && StringUtils.isNotBlank(dto.getPrincipalPhone())){
			// 查询的当前是否注册
			SysUserDTO byUsername = sysUserService.getByUsername(dto.getPrincipalPhone());
			if(null == byUsername){// 为注册
				SysUserEntity sysUserDTO = new SysUserEntity();
				sysUserDTO.setMobile(dto.getPrincipalPhone());
				sysUserDTO.setUsername(dto.getPrincipalPhone());
				sysUserDTO.setRealName(dto.getPrincipalName());
				// Long deptId = SecurityUser.getUser().getDeptId();// 原本的部门id
				// SecurityUser.getUser().setDeptId(sysDeptEntity.getId());// 想要变更的
				sysUserDTO.setDeptId(sysDeptEntity.getId());
				String password =  "12345678";
				sysUserDTO.setPassword(DigestUtils.sha256Hex(password));
				sysUserDTO.setIsCompany(1);
				sysUserDTO.setIsDeptHead(0);
				sysUserService.insert(sysUserDTO);
				userId = sysUserDTO.getId();
				// SecurityUser.getUser().setDeptId(deptId);// 改回去
				// 发送短信
				LinkedHashMap<String,String> map=new LinkedHashMap<>();
				map.put("code",password);
				sysSmsService.send(dto.getPrincipalPhone(),map,signName,template);

			}else {// 已注册
				userId = byUsername.getId();
				byUsername.setIsCompany(1);
				byUsername.setIsDeptHead(0);
				byUsername.setDeptId(sysDeptEntity.getId());
				sysUserService.updateDTO(byUsername);
			}
			// 给用户添加角色
			if(userId != 0L){
				// 查询用户是否有当前角色
				Long roleUserId = sysRoleUserService.getRoleUserId(userId, 1108900445339840513L);
				if(roleUserId == null){
					// 添加角色
					SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
					sysRoleUserEntity.setRoleId(1108900445339840513L);
					sysRoleUserEntity.setUserId(userId);
					sysRoleUserService.insert(sysRoleUserEntity);
				}
			}
		}
	}

	@Override
	@Transactional
	public void organUpdate(SysDeptDTO dto) {
		long userId = 0L;
		//效验数据
		//ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
		// 组织架构的更新
		SysDeptEntity sysDeptEntity = this.selectById(dto.getId());
		sysDeptEntity.setName(dto.getName());
		sysDeptEntity.setPrincipalPhone(dto.getPrincipalPhone());
		sysDeptEntity.setPrincipalName(dto.getPrincipalName());
		this.updateById(sysDeptEntity);
		if(dto.getIsCom() == 0 && StringUtils.isNotBlank(dto.getPrincipalPhone())){
			// 查询的当前是否注册
			SysUserDTO byUsername = sysUserService.getByUsername(dto.getPrincipalPhone());
			if(null == byUsername){// 为注册
				SysUserEntity sysUserDTO = new SysUserEntity();
				sysUserDTO.setMobile(dto.getPrincipalPhone());
				sysUserDTO.setUsername(dto.getPrincipalPhone());
				sysUserDTO.setRealName(dto.getPrincipalName());
				// Long deptId = SecurityUser.getUser().getDeptId();// 原本的部门id
				// SecurityUser.getUser().setDeptId(sysDeptEntity.getId());// 想要变更的
				sysUserDTO.setDeptId(sysDeptEntity.getId());
				String password =  "12345678";
				sysUserDTO.setPassword(DigestUtils.sha256Hex(password));
				sysUserDTO.setIsCompany(1);
				sysUserDTO.setIsDeptHead(0);
				sysUserService.insert(sysUserDTO);
				userId = sysUserDTO.getId();
				// SecurityUser.getUser().setDeptId(deptId);// 改回去
				// 发送短信
				LinkedHashMap<String,String> map=new LinkedHashMap<>();
				map.put("code",password);
				sysSmsService.send(dto.getPrincipalPhone(),map,signName,template);

			}else {// 已注册
				userId = byUsername.getId();
				byUsername.setIsCompany(1);
				byUsername.setIsDeptHead(0);
				byUsername.setDeptId(sysDeptEntity.getId());
				sysUserService.updateDTO(byUsername);
			}
			// 给用户添加角色
			if(userId != 0L){
				// 查询用户是否有当前角色
				Long roleUserId = sysRoleUserService.getRoleUserId(userId, 1108900445339840513L);
				if(roleUserId == null){
					// 添加角色
					SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
					sysRoleUserEntity.setRoleId(1108900445339840513L);
					sysRoleUserEntity.setUserId(userId);
					sysRoleUserService.insert(sysRoleUserEntity);
				}
			}
		}
	}
}
