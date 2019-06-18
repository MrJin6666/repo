/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.sys.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.exception.ErrorCode;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.modules.message.service.SysSmsService;
import com.goboosoft.modules.security.password.PasswordUtils;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.security.user.UserDetail;
import com.goboosoft.modules.sys.dto.PasswordDTO;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.entity.SysRoleUserEntity;
import com.goboosoft.modules.sys.entity.SysUserEntity;
import com.goboosoft.modules.sys.excel.SysUserExcel;
import com.goboosoft.modules.sys.service.SysRoleUserService;
import com.goboosoft.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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


	@GetMapping("userChildren")
	@ApiOperation("查询监管部门下的企业创建人")
	public Result<List<SysUserDTO>> userChildren(){
		List<SysUserDTO> roleIdList = sysUserService.findUserChildren(SecurityUser.getDeptId());
		return new Result<List<SysUserDTO>>().ok(roleIdList);
	}

	@GetMapping("deptUserChildren")
	@ApiOperation("查询子部门及本部门的人员")
	public Result<List<SysUserDTO>> findDeptUserChildren(){
		List<SysUserDTO> roleIdList = sysUserService.findDeptUserChildren(SecurityUser.getDeptId());
		return new Result<List<SysUserDTO>>().ok(roleIdList);
	}



	@GetMapping("info")
	@ApiOperation("登录用户信息")
	public Result<SysUserDTO> info(){
		SysUserDTO data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDTO.class);
		return new Result<SysUserDTO>().ok(data);
	}

	@GetMapping("info/{id}")
	@ApiOperation("用户信息")
	public Result<SysUserDTO> info(@PathVariable("id") String id){
		SysUserDTO sysUserDTO = sysUserService.get(Long.valueOf(id));
		return new Result<SysUserDTO>().ok(sysUserDTO);
	}

	@PutMapping("password")
	@ApiOperation("修改密码")
	@LogOperation("修改密码")
	public Result password(@RequestBody PasswordDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto);

		UserDetail user = SecurityUser.getUser();

		//原密码不正确
		if(!DigestUtils.sha256Hex(dto.getPassword()).equals(user.getPassword())){
			return new Result().error(ErrorCode.PASSWORD_ERROR);
		}

		sysUserService.updatePassword(user.getId(), dto.getNewPassword());

		return new Result();
	}


	@PutMapping("audit")
	@ApiOperation("行业审核")
	@LogOperation("行业审核")
	public Result audit(@RequestBody SysUserDTO dto){
		// 修改行业
		SysUserEntity sysUserDTO = sysUserService.selectById(dto.getId());
		sysUserDTO.setIsDeptHead(dto.getIsDeptHead());
		sysUserDTO.setDeptId(dto.getDeptId());
		sysUserDTO.setStatus(dto.getStatus());
		if(sysUserDTO.getStatus() == 3){
			sysUserDTO.setCause(dto.getCause());
		}else {
			sysUserDTO.setCause(null);
		}
		sysUserService.updateById(sysUserDTO);
		return new Result();
	}


	@PutMapping("move")
	@ApiOperation("行业移交")
	@LogOperation("行业移交")
	public Result move(@RequestBody SysUserDTO dto){
		// 修改行业
		SysUserDTO sysUserDTO = sysUserService.get(SecurityUser.getUserId());
		sysUserDTO.setIsDeptHead(dto.getIsDeptHead());
		sysUserService.update(sysUserDTO);
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

	@PutMapping("one")
	@ApiOperation("修改")
	@LogOperation("修改")
	public Result update(@RequestBody SysUserDTO dto){
		//效验数据
		//ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
		SysUserDTO sysUserDTO = sysUserService.get(dto.getId());
		sysUserDTO.setIsDeptHead(dto.getIsDeptHead());
		sysUserDTO.setDeptId(dto.getDeptId());
		sysUserService.updateDTO(sysUserDTO);
		return new Result();
	}

	@PostMapping("one")
	@ApiOperation("管理端添加用户")
	@LogOperation("管理端添加用户")
	public Result addUserSave(@RequestBody SysUserDTO dto) {
		sysUserService.addUserSave(dto);
		return new Result();
	}

	@DeleteMapping("{id}")
	@ApiOperation("删除")
	@LogOperation("删除")
	public Result delete(@PathVariable("id") String id){
		//效验数据
		// AssertUtils.isArrayEmpty(ids, "id");
		sysUserService.deleteById(Long.valueOf(id));
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
	@GetMapping("getUserDeptName")
	@ApiOperation("获取监管部门负责人")
	public Result<List<SysUserDTO>> getUserDeptName(@RequestParam(value = "id",required = true) String id){
		List<SysUserDTO> deptList = sysUserService.getUserDeptName(id);
		return new Result<List<SysUserDTO>>().ok(deptList);
	}
}