/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.BaseService;
import com.goboosoft.common.utils.Result;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDTO> getUserManage(Long companyId);

	PageData<SysUserDTO> page(Map<String, Object> params);

	List<SysUserDTO> list(Map<String, Object> params);

	SysUserDTO get(Long id);

	SysUserDTO getByUsername(String username);

	void save(SysUserDTO dto);

	void update(SysUserDTO dto);

	void delete(Long[] ids);

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
	/**
	 * 判断手机号是否被注册
	 */
	Boolean isExist(String mobile);
	/**
	 * 生成手机验证码
	 */
	String createCaptcha();
	/**
	 * 将验证码存在redis
	 **/

	void saveCaptchaInRedis(String moblie,String captcha);
	/**
	 * 通过电话号码获取user
	 **/
	SysUserDTO getByPhone(String phone);

	/**
	 * 获取除了身份证号码及电话的信息
	 * @param userId
	 * @return
	 */
    SysUserDTO getUser(Long userId);

	/**
	 * 更新公司的id
	 * @param id
	 * @param userId
	 * @return
	 */
    Integer updateCompanyId(Long id, Long userId);

	Boolean checkCaptcha(String mobile, String captcha);

    SysUserDTO getCompanyId(Long companyId);

	/**
	 * dmxuan
	 * 通过手机号获取用户id
	 * @Date 2019/2/19 10:30
	 * @Param
	 * @return
	 **/

	Long selectByMobile(String mobile);

	/**
	 * 通过部门id选择负责人
	 * @param deptId
	 * @return
	 */
    List<SysUserDTO> getUserDeptName(String deptId);
	/**
	 * 更改公司id
	 * @param userId
	 * @return
	 */
    Integer updateByUserId(Long userId);
	/**
	 * 获取纬度人员
	 * @param companyId
	 * @return
	 */
	List<SysUserDTO> getUserCompany(Long companyId);
	/**
	 * 获取行业端未读人员
	 * @param deptId
	 * @return
	 */
	List<SysUserDTO> getUserDept(Long deptId);

	Result sendCaptcha(String mobile, int type);
	/**
	 * 修改头像及昵称
	 * @param map,userId
	 * @return
	 */
    void updateHeadurlAndUserName(Map<String, String> map, Long userId);

    void updateSuperAdmin(Long userId, int superAdmin);

	/**
	 *
	 * 更换推送id
	 * @param registrationId
	 * @return
	 */
	Integer updateRegistrationId(String registrationId,Long id);
}
