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
import com.goboosoft.common.exception.RenException;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.modules.message.service.SysSmsService;
import com.goboosoft.modules.security.password.PasswordUtils;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.security.user.UserDetail;
import com.goboosoft.modules.sys.dao.SysUserDao;
import com.goboosoft.modules.sys.dto.SysUserDTO;
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

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysRoleUserService sysRoleUserService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysSmsService sysSmsService;
	@Value("${SMS.signName}")
	private String signName;
	@Value("${SMS.template}")
	private String template;

	@Override
	public PageData<SysUserDTO> page(Map<String, Object> params) {
		//转换成like
		paramsToLike(params, "username");

		//分页
		IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);

		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
		}

		//查询
		List<SysUserEntity> list = baseDao.getList(params);

		return getPageData(list, page.getTotal(), SysUserDTO.class);
	}

	@Override
	public List<SysUserDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
		}

		List<SysUserEntity> entityList = baseDao.getList(params);

		return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);
	}

	@Override
	public SysUserDTO get(Long id) {
		SysUserEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	public SysUserDTO getByUsername(String username) {
		SysUserEntity entity = baseDao.getByUsername(username);
		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		String password = DigestUtils.sha256Hex(entity.getPassword());
		entity.setPassword(password);

		//保存用户
		entity.setSuperAdmin(SuperAdminEnum.NO.value());
		baseDao.insert(entity);

		//保存角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);
		//更新用户
		updateById(entity);
		//更新角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	public void updateDTO(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);
		baseDao.updateById(entity);
	}

	@Override
	public void delete(Long[] ids) {
		//删除用户
		baseDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		sysRoleUserService.deleteByUserIds(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(Long id, String newPassword) {
		newPassword = DigestUtils.sha256Hex(newPassword);

		baseDao.updatePassword(id, newPassword);
	}

	@Override
	public SysUserDTO getCompanyId(Long companyId){
		return baseDao.getCompanyId(companyId);
	}

	@Override
	public int getCountByDeptId(Long deptId) {
		return baseDao.getCountByDeptId(deptId);
	}

	@Override
	public List<SysUserDTO> findUserChildren(Long deptId) {
		return baseDao.findUserChildren(deptId);
	}

	@Override
	public List<SysUserDTO> findDeptUserChildren(Long deptId) {
		return baseDao.findDeptUserChildren(deptId);
	}
	@Override
	public List<SysUserDTO> getUserDeptName(String deptId){
		return baseDao.getUserDeptName(Long.valueOf(deptId));
	}

	@Override
	@Transactional
	public void addUserSave(SysUserDTO dto) {
		long userId = 0L;
		if(dto.getDeptId() == null){
			throw new RenException("部门不能为空！");
		}
		if(StringUtils.isNotBlank(dto.getMobile())){
			// 查询的当前是否注册
			SysUserDTO byUsername = this.getByUsername(dto.getMobile());
			if(null == byUsername){// 为注册
				SysUserEntity sysUserDTO = new SysUserEntity();
				sysUserDTO.setMobile(dto.getMobile());
				sysUserDTO.setUsername(dto.getMobile());
				sysUserDTO.setDeptId(dto.getDeptId());
				sysUserDTO.setRealName(dto.getRealName());
				sysUserDTO.setIsCompany(1);
				String password =  "12345678";
				sysUserDTO.setPassword(DigestUtils.sha256Hex(password));
				this.insert(sysUserDTO);
				userId = sysUserDTO.getId();
				// 发送短信
				LinkedHashMap<String,String> map=new LinkedHashMap<>();
				map.put("code",password);
				sysSmsService.send(dto.getMobile(),map,signName,template);

			}else {// 已注册
				userId = byUsername.getId();
				byUsername.setDeptId(dto.getId());
				this.updateDTO(byUsername);
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
