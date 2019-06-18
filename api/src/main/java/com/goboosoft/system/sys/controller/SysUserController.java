/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.controller;

import ch.qos.logback.core.net.LoginAuthenticator;
import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.exception.ErrorCode;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.IpUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.system.log.entity.SysLogLoginEntity;
import com.goboosoft.system.log.enums.LoginOperationEnum;
import com.goboosoft.system.log.enums.LoginStatusEnum;
import com.goboosoft.system.log.service.SysLogLoginService;
import com.goboosoft.system.security.controller.LoginController;
import com.goboosoft.system.security.service.SysUserTokenService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.PasswordDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.excel.SysUserExcel;
import com.goboosoft.system.sys.service.SysRoleUserService;
import com.goboosoft.system.security.password.PasswordUtils;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户管理
 * 
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags="用户管理")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysLogLoginService sysLogLoginService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
		@ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType="String")
	})
	@RequiresPermissions("sys:user:page")
	public Result<PageData<SysUserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SysUserDTO> page = sysUserService.page(params);

		return new Result<PageData<SysUserDTO>>().ok(page);
	}


	@GetMapping("/getUserManage")
	@ApiOperation("分页")
//	@RequiresPermissions("sys:user:page")
	public Result<PageData<SysUserDTO>> getUserManage(){

		PageData<SysUserDTO> page = sysUserService.getUserManage(SecurityUser.getUser().getCompanyId());

		return new Result<PageData<SysUserDTO>>().ok(page);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("sys:user:info")
	public Result<SysUserDTO> get(@PathVariable("id") Long id){
		SysUserDTO data = sysUserService.get(id);

		//用户角色列表
		List<Long> roleIdList = sysRoleUserService.getRoleIdList(id);
		data.setRoleIdList(roleIdList);

		return new Result<SysUserDTO>().ok(data);
	}

	@GetMapping("info")
	@ApiOperation("登录用户信息")
	public Result<SysUserDTO> info(){
		SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
		return new Result<SysUserDTO>().ok(data);
	}

	@PutMapping("password")
	@ApiOperation("修改密码")
	@LogOperation("修改密码")
	public Result password(@RequestBody PasswordDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto);

		UserDetail user = SecurityUser.getUser();

		//原密码不正确
		if(!PasswordUtils.matches(dto.getPassword(), user.getPassword())){
			return new Result().error(ErrorCode.PASSWORD_ERROR);
		}

		sysUserService.updatePassword(user.getId(), dto.getNewPassword());

		return new Result();
	}

	@PostMapping
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("sys:user:save")
	public Result save(@RequestBody SysUserDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

		sysUserService.save(dto);

		return new Result();
	}

	@PutMapping
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("sys:user:update")
	public Result update(@RequestBody SysUserDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		sysUserService.update(dto);

		return new Result();
	}

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("sys:user:delete")
	public Result delete(@RequestBody Long[] ids){
		//效验数据
		AssertUtils.isArrayEmpty(ids, "id");

		sysUserService.deleteBatchIds(Arrays.asList(ids));

		return new Result();
	}

	@GetMapping("export")
	@ApiOperation("导出")
	@LogOperation("导出")
	@RequiresPermissions("sys:user:export")
	@ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType="String")
	public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		List<SysUserDTO> list = sysUserService.list(params);

		ExcelUtils.exportExcelToTarget(response, null, list, SysUserExcel.class);
	}

    @PostMapping("updatePhoneSendCaptcha")
    @ApiOperation(value = "更换企业用户手机号发送验证码")
    public Result updatePhoneSendCaptcha(@RequestParam("mobile") String mobile){

        Boolean exist = sysUserService.isExist(mobile);
        if(!exist){
            return new Result().error("此手机号已被注册，不能发送验证码！");
        }

        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        String captcha=sysUserService.createCaptcha();
        map.put("code",captcha);
//        sysSmsService.send(mobile,map,signName,template);
        sysUserService.saveCaptchaInRedis(mobile,captcha);
        return new Result().ok("验证码为："+captcha);

    }


	@PostMapping("updateSysUserPhone")
	@ApiOperation(value = "更换企业用户手机号")
	public Result updateSysUserPhone(@RequestBody Map<String,String> params){

		String phone = params.get("mobile");
        String captcha = params.get("captcha");
        Boolean checkCaptcha = sysUserService.checkCaptcha(phone, captcha);
        if (!checkCaptcha){
            return new Result().ok("验证码错误或失效！");
        }

        Boolean exist = sysUserService.isExist(phone);
        if(!exist){
            return new Result().error("此手机号已被注册，不能更改为此手机号！");
        }

        Long userId = SecurityUser.getUserId();
		SysUserDTO sysUserDTO = sysUserService.get(userId);
		sysUserDTO.setMobile(phone);

		sysUserService.update(sysUserDTO);

		return new Result();
	}


    @PostMapping("removeSysUser")
    @ApiOperation(value = "移除企业用户")
    public Result removeSysUser(){
        Long userId = SecurityUser.getUserId();
        SysUserDTO user = sysUserService.get(userId);
        user.setStatus(0);//改为不可用（0：不可用  1：正常）
        sysUserService.update(user);
        return new Result();
    }


    @PostMapping("loginOutSysUser")
    @ApiOperation(value = "退出企业用户账号")
    public Result loginOutSysUser(HttpServletRequest request){
		UserDetail user = SecurityUser.getUser();

		//退出
		sysUserTokenService.logout(user.getId());

		//用户信息
		SysLogLoginEntity log = new SysLogLoginEntity();
		log.setOperation(LoginOperationEnum.LOGOUT.value());
		log.setIp(IpUtils.getIpAddr(request));
		log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		log.setIp(IpUtils.getIpAddr(request));
		log.setStatus(LoginStatusEnum.SUCCESS.value());
		log.setCreator(user.getId());
		log.setCreatorName(user.getUsername());
		log.setCreateDate(new Date());
		sysLogLoginService.save(log);

		return new Result();
    }
	@GetMapping("getUserDeptName")
	@ApiOperation("获取监管部门负责人")
	public Result<List<SysUserDTO>> getUserDeptName(@RequestParam(value = "id",required = true) String id){
		List<SysUserDTO> deptList = sysUserService.getUserDeptName(id);
		return new Result<List<SysUserDTO>>().ok(deptList);
	}
}