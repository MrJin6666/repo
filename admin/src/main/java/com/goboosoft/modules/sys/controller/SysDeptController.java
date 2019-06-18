/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.sys.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.DeptTreeUtil;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.modules.message.service.SysSmsService;
import com.goboosoft.modules.security.password.PasswordUtils;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.sys.dto.OrganUserDTO;
import com.goboosoft.modules.sys.dto.SysDeptDTO;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.entity.SysDeptEntity;
import com.goboosoft.modules.sys.entity.SysRoleUserEntity;
import com.goboosoft.modules.sys.entity.SysUserEntity;
import com.goboosoft.modules.sys.service.SysDeptService;
import com.goboosoft.modules.sys.service.SysRoleUserService;
import com.goboosoft.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "部门管理")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserService sysUserService;




    @GetMapping("organList")
    @ApiOperation("查询组织架构")
    public Result<List<SysDeptDTO>> organList() {
        // 根据账号查询它当前的部门id
        List<SysDeptDTO> treeDept = null;
        SysUserEntity sysUserEntity = sysUserService.selectById(SecurityUser.getUserId());
        List<SysDeptDTO> list = sysDeptService.findAll();
        if(sysUserEntity.getSuperAdmin() != null && sysUserEntity.getSuperAdmin().equals(1)){
            treeDept = DeptTreeUtil.createTreeDept(null, list);
        }else {
            if(sysUserEntity.getIsDeptHead() == 1){
                treeDept = DeptTreeUtil.createTreeDept(String.valueOf(sysUserEntity.getDeptId()), list);
            }else
            if(sysUserEntity.getIsDeptHead() == 0){
                treeDept = DeptTreeUtil.createTreeDeptParent(String.valueOf(sysUserEntity.getDeptId()), list);
            }
        }
        return new Result<List<SysDeptDTO>>().ok(treeDept);
    }

    @GetMapping("organListUser")
    @ApiOperation("查询组织架构下以及同级的人员名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = "deptId", value = "部门id", paramType = "query", dataType="String"),
            @ApiImplicitParam(name = "userName", value = "用户名称", paramType = "query", dataType="String")
    })
    public Result<PageData<OrganUserDTO>> organListUser(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<OrganUserDTO> list =  sysDeptService.findOrganList(params);
        return new Result<PageData<OrganUserDTO>>().ok(list);
    }

    @PostMapping("organSave")
    @ApiOperation("组织架构的新增")
    @LogOperation("组织架构的新增")
    public Result organSave(@RequestBody SysDeptDTO dto) {
        sysDeptService.organSave(dto);
        return new Result();
    }

    @PutMapping("organSave")
    @ApiOperation("组织架构的更新")
    @LogOperation("组织架构的更新")
    public Result organUpdate(@RequestBody SysDeptDTO dto) {
        sysDeptService.organUpdate(dto);
        return new Result();
    }


    @GetMapping("childList")
    @ApiOperation("查询子部门企")
    public Result<List<SysDeptDTO>> childList() {
        int isDeptHead = sysUserService.get(SecurityUser.getUserId()).getIsDeptHead();
        List<SysDeptDTO> list = sysDeptService.childList(SecurityUser.getDeptId(),isDeptHead);
        return new Result<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("childListDept")
    @ApiOperation("查询子部门")
    public Result<List<SysDeptDTO>> childListDept() {
        int isDeptHead = sysUserService.get(SecurityUser.getUserId()).getIsDeptHead();
        List<SysDeptDTO> list = sysDeptService.childListDept(SecurityUser.getDeptId(),isDeptHead);
        return new Result<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("list")
    @ApiOperation("列表")
    public Result<List<SysDeptDTO>> list() {
        List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));

        return new Result<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<SysDeptDTO> get(@PathVariable("id") Long id) {
        SysDeptDTO data = sysDeptService.get(id);

        return new Result<SysDeptDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody SysDeptDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysDeptService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody SysDeptDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDeptService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        sysDeptService.delete(id);

        return new Result();
    }

}