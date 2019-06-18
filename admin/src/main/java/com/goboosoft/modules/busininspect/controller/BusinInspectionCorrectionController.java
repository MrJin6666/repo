package com.goboosoft.modules.busininspect.controller;

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
import com.goboosoft.modules.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.modules.busininspect.excel.BusinInspectionCorrectionExcel;
import com.goboosoft.modules.busininspect.service.BusinInspectionCorrectionService;
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
 * 业务 - 整改基本信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@RestController
@RequestMapping("demo/busininspectioncorrection")
@Api(tags="业务 - 整改基本信息表")
public class BusinInspectionCorrectionController {
    @Autowired
    private BusinInspectionCorrectionService businInspectionCorrectionService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:busininspectioncorrection:page")
    public Result<PageData<BusinInspectionCorrectionDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BusinInspectionCorrectionDTO> page = businInspectionCorrectionService.page(params);

        return new Result<PageData<BusinInspectionCorrectionDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:busininspectioncorrection:info")
    public Result<BusinInspectionCorrectionDTO> get(@PathVariable("id") Long id){
        BusinInspectionCorrectionDTO data = businInspectionCorrectionService.get(id);

        return new Result<BusinInspectionCorrectionDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:busininspectioncorrection:save")
    public Result save(@RequestBody BusinInspectionCorrectionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        businInspectionCorrectionService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:busininspectioncorrection:update")
    public Result update(@RequestBody BusinInspectionCorrectionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        businInspectionCorrectionService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:busininspectioncorrection:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        businInspectionCorrectionService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("demo:busininspectioncorrection:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BusinInspectionCorrectionDTO> list = businInspectionCorrectionService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, BusinInspectionCorrectionExcel.class);
    }

}