/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.redis.RedisUtils;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.peoplemanagement.dto.PeopleManagementDTO;
import com.goboosoft.system.message.service.SysSmsService;
import com.goboosoft.system.security.password.PasswordUtils;
import com.goboosoft.system.security.service.SysUserTokenService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dao.SysUserDao;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import com.goboosoft.system.sys.enums.SuperAdminEnum;
import com.goboosoft.system.sys.service.SysDeptService;
import com.goboosoft.system.sys.service.SysRoleUserService;
import com.goboosoft.system.sys.service.SysUserService;
import com.google.common.cache.Cache;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;


import java.util.*;
import java.util.concurrent.TimeUnit;


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
	private RedisUtils redisUtils;
	@Autowired
	private SysSmsService sysSmsService;
	@Value("${SMS.signName}")
	private String signName;
	@Value("${SMS.template}")
	private String template;

	@Autowired
	private SysUserTokenService sysUserTokenService;


	// 本地缓存
	private Cache<String, String> localCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.MINUTES).build();
	@Value("${renren.redis.open: true}")
	private boolean open;

    @Override
    public PageData<SysUserDTO> getUserManage(Long companyId) {
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("company_id", companyId);
        IPage iPage = baseDao.selectPage(new Page<>(), wrapper);
        List<PeopleManagementDTO> targetList = ConvertUtils.sourceToTarget( iPage.getRecords(), PeopleManagementDTO.class);
        return  new PageData(targetList,iPage.getTotal());
    }
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
		String password = PasswordUtils.encode(entity.getPassword());
		entity.setPassword(password);

		//保存用户
		entity.setSuperAdmin(SuperAdminEnum.NO.value());
		insert(entity);

		//保存角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		if(StringUtils.isBlank(dto.getPassword())){
			entity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(entity.getPassword());
			entity.setPassword(password);
		}

		//更新用户
		updateById(entity);

		//更新角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
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
		sysUserTokenService.logout(id);
	}

	@Override
	public int getCountByDeptId(Long deptId) {
		return baseDao.getCountByDeptId(deptId);
	}

	@Override
	public Boolean isExist(String mobile){
		Boolean result = false;
		QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<SysUserEntity>();
		wrapper.eq("mobile",mobile);
		List<SysUserEntity> sysUserEntities = baseDao.selectList(wrapper);
		//根据查询的数量判断用户是否存在
		if(sysUserEntities.size()==0) {
			result = true;
		}
		return result;
	}

	@Override
	public String createCaptcha() {
		String captcha= String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		System.out.println(captcha);
		return captcha;
	}

	@Override
	public void saveCaptchaInRedis(String mobile,String captcha){
		if(open){
			//短信设置30分钟的有效期
			redisUtils.set(mobile,captcha, RedisUtils.MINUTE_THIRTY_EXPIRE);
		}else{
			System.out.print("存储到本地缓存.......");
			localCache.put(mobile,captcha);
		}
	}

	@Override
	public SysUserDTO getByPhone(String mobile){
		QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<SysUserEntity>();
		wrapper.eq("mobile",mobile);
		return ConvertUtils.sourceToTarget(baseDao.selectOne(wrapper),SysUserDTO.class);
	}

	@Override
	public SysUserDTO getUser(Long userId){
		SysUserDTO user = baseDao.getUser(userId);
		if(user.getIsCompany()==Constant.CompanyService.INDUSTRY.value()) {
			user.setDeptName(sysDeptService.get(user.getDeptId()).getName());
		}
		return user;
	}

	@Override
    public Integer updateCompanyId(Long companyId,Long userId){
	    return baseDao.updateCompanyId(companyId,userId);
    }

	@Override
	public Boolean checkCaptcha(String mobile, String inputCaptcha) {
		String captcha = "";
		if (open) {
			captcha = String.valueOf(redisUtils.get(mobile, RedisUtils.MINUTE_THIRTY_EXPIRE));
		} else {
			captcha = localCache.getIfPresent(mobile);
		}
		if (inputCaptcha.equals(captcha)) {
			if (open) {
				redisUtils.delete(mobile);
			} else {
				localCache.invalidate(mobile);
			}
			return true;
		}
		return false;
	}

	@Override
	public SysUserDTO getCompanyId(Long companyId){
    	return baseDao.getCompanyId(companyId);
	}

	@Override
	public Long selectByMobile(String mobile) {
		return baseDao.selectByMobile(mobile);
	}

	@Override
	public List<SysUserDTO> getUserDeptName(String deptId){
		return baseDao.getUserDeptName(Long.valueOf(deptId));
	}

	@Override
	public Integer updateByUserId(Long userId){
    	return baseDao.updateByUserId(userId);
	}

	@Override
	public List<SysUserDTO> getUserCompany(Long companyId){
    	return baseDao.getUserCompany(companyId);
	}

	@Override
	public List<SysUserDTO> getUserDept(Long deptId){
    	return baseDao.getUserDept(deptId);
	}

	@Override
	public Result sendCaptcha(String mobile, int type) {
		Long userId = this.selectByMobile(mobile);

		if (type==1){
			if(null!=userId){
				return new Result().error("该手机号已被注册！");
			}
		}
		if (type==2){
			if(null==userId){
				return new Result().error("该手机号尚未注册");
			}
		}
		if (type==3){
			if(!userId.equals(SecurityUser.getUserId())){
				return new Result().error("该手机号非当前用户绑定手机号！");
			}
		}


		LinkedHashMap<String,String> map=new LinkedHashMap<>();
		String captcha=this.createCaptcha();
		map.put("code",captcha);
		sysSmsService.send(mobile,map,signName,template);
		this.saveCaptchaInRedis(mobile,captcha);
		return new Result().ok("短信发送成功!");
//        return new Result().ok("验证码为："+captcha);
	}

	@Override
	public void updateHeadurlAndUserName(Map<String, String> map, Long userId){

         baseDao.updateHeadurlAndUserName(map,userId);
	}

	@Override
	public void updateSuperAdmin(Long userId, int superAdmin){
    	baseDao.updateSuperAdmin(userId,superAdmin);
	}

	@Override
	public Integer updateRegistrationId(String registrationId,Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("registrationId",registrationId);
		map.put("id",id);
		return baseDao.updateRegistrationId(map);
	}
}
