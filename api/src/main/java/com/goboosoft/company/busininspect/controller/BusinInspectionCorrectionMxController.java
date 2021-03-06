package com.goboosoft.company.busininspect.controller;

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
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionMxDTO;
import com.goboosoft.company.busininspect.excel.BusinInspectionCorrectionMxExcel;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionMxService;
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
 * 业务 - 整改明细信息表
 *
 * @author DMXUAN
 */
@RestController
@RequestMapping("busininspect/busininspectioncorrectionmx")
@Api(tags="业务 - 整改明细信息表")
public class BusinInspectionCorrectionMxController {
    @Autowired
    private BusinInspectionCorrectionMxService businInspectionCorrectionMxService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("busininspect:busininspectioncorrectionmx:page")
    public Result<PageData<BusinInspectionCorrectionMxDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BusinInspectionCorrectionMxDTO> page = businInspectionCorrectionMxService.page(params);

        return new Result<PageData<BusinInspectionCorrectionMxDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
//    @RequiresPermissions("busininspect:busininspectioncorrectionmx:info")
    public Result<BusinInspectionCorrectionMxDTO> get(@PathVariable("id") Long id){
        BusinInspectionCorrectionMxDTO data = businInspectionCorrectionMxService.get(id);

        return new Result<BusinInspectionCorrectionMxDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("busininspect:busininspectioncorrectionmx:save")
    public Result save(@RequestBody BusinInspectionCorrectionMxDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        businInspectionCorrectionMxService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("busininspect:busininspectioncorrectionmx:update")
    public Result update(@RequestBody BusinInspectionCorrectionMxDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        businInspectionCorrectionMxService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("busininspect:busininspectioncorrectionmx:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        businInspectionCorrectionMxService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("busininspect:busininspectioncorrectionmx:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BusinInspectionCorrectionMxDTO> list = businInspectionCorrectionMxService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, BusinInspectionCorrectionMxExcel.class);
    }

}