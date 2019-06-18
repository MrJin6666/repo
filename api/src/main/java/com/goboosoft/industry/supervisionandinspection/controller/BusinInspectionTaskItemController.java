package com.goboosoft.industry.supervisionandinspection.controller;

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
import com.goboosoft.industry.supervisionandinspection.dto.BusinInspectionTaskItemDTO;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * 业务 - 督导检查任务项信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@RestController
@RequestMapping("demo/busininspectiontaskitem")
@Api(tags="业务 - 督导检查任务项信息表")
public class BusinInspectionTaskItemController {
    @Autowired
    private BusinInspectionTaskItemService businInspectionTaskItemService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:busininspectiontaskitem:page")
    public Result<PageData<BusinInspectionTaskItemDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BusinInspectionTaskItemDTO> page = businInspectionTaskItemService.page(params);

        return new Result<PageData<BusinInspectionTaskItemDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:busininspectiontaskitem:info")
    public Result<BusinInspectionTaskItemDTO> get(@PathVariable("id") Long id){
        BusinInspectionTaskItemDTO data = businInspectionTaskItemService.get(id);

        return new Result<BusinInspectionTaskItemDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:busininspectiontaskitem:save")
    public Result save(@RequestBody BusinInspectionTaskItemDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        businInspectionTaskItemService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:busininspectiontaskitem:update")
    public Result update(@RequestBody BusinInspectionTaskItemDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        businInspectionTaskItemService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:busininspectiontaskitem:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        businInspectionTaskItemService.delete(ids);

        return new Result();
    }
}