package com.goboosoft.modules.companymenuuser.controller;


import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.modules.companymenuuser.dto.CompanyUserMenuDTO;
import com.goboosoft.modules.companymenuuser.excel.CompanyUserMenuExcel;
import com.goboosoft.modules.companymenuuser.service.CompanyUserMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@RestController
@RequestMapping("companyusermenu")
@Api(tags="用户企业菜单表")
public class CompanyUserMenuController {
    @Autowired
    private CompanyUserMenuService companyUserMenuService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:companyusermenu:page")
    public Result<PageData<CompanyUserMenuDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CompanyUserMenuDTO> page = companyUserMenuService.page(params);

        return new Result<PageData<CompanyUserMenuDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:companyusermenu:info")
    public Result<CompanyUserMenuDTO> get(@PathVariable("id") Long id){
        CompanyUserMenuDTO data = companyUserMenuService.get(id);

        return new Result<CompanyUserMenuDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:companyusermenu:save")
    public Result save(@RequestBody CompanyUserMenuDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        companyUserMenuService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:companyusermenu:update")
    public Result update(@RequestBody CompanyUserMenuDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        companyUserMenuService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:companyusermenu:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        companyUserMenuService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("demo:companyusermenu:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CompanyUserMenuDTO> list = companyUserMenuService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CompanyUserMenuExcel.class);
    }

}