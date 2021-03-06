package com.goboosoft.modules.basicmeasure.controller;

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
import com.goboosoft.modules.basicmeasure.dto.BasicMeasureDTO;
import com.goboosoft.modules.basicmeasure.excel.BasicMeasureExcel;
import com.goboosoft.modules.basicmeasure.service.BasicMeasureService;
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
 * 计量单位
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@RestController
@RequestMapping("basicmeasure/basicmeasure")
@Api(tags="计量单位")
public class BasicMeasureController {
    @Autowired
    private BasicMeasureService basicMeasureService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = "name", value = "计量单位名称", paramType = "query",required = false, dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<BasicMeasureDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BasicMeasureDTO> page = basicMeasureService.page(params);

        return new Result<PageData<BasicMeasureDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<BasicMeasureDTO> get(@PathVariable("id") Long id){
        BasicMeasureDTO data = basicMeasureService.get(id);

        return new Result<BasicMeasureDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody BasicMeasureDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        basicMeasureService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody BasicMeasureDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        basicMeasureService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        basicMeasureService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BasicMeasureDTO> list = basicMeasureService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, BasicMeasureExcel.class);
    }

}