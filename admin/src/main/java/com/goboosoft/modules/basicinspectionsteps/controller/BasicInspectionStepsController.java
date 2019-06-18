package com.goboosoft.modules.basicinspectionsteps.controller;

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
import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import com.goboosoft.modules.basicinspectionsteps.excel.BasicInspectionStepsExcel;
import com.goboosoft.modules.basicinspectionsteps.service.BasicInspectionStepsService;
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
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@RestController
@RequestMapping("basicinspectionsteps/basicinspectionsteps")
@Api(tags="基础 - 检查项步骤信息表")
public class BasicInspectionStepsController {
    @Autowired
    private BasicInspectionStepsService basicInspectionStepsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("basicinspectionsteps:basicinspectionsteps:page")
    public Result<PageData<BasicInspectionStepsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BasicInspectionStepsDTO> page = basicInspectionStepsService.page(params);

        return new Result<PageData<BasicInspectionStepsDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("basicinspectionsteps:basicinspectionsteps:info")
    public Result<BasicInspectionStepsDTO> get(@PathVariable("id") Long id){
        BasicInspectionStepsDTO data = basicInspectionStepsService.get(id);

        return new Result<BasicInspectionStepsDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("basicinspectionsteps:basicinspectionsteps:save")
    public Result save(@RequestBody BasicInspectionStepsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        basicInspectionStepsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("basicinspectionsteps:basicinspectionsteps:update")
    public Result update(@RequestBody BasicInspectionStepsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        basicInspectionStepsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("basicinspectionsteps:basicinspectionsteps:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        basicInspectionStepsService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("basicinspectionsteps:basicinspectionsteps:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BasicInspectionStepsDTO> list = basicInspectionStepsService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, BasicInspectionStepsExcel.class);
    }

}