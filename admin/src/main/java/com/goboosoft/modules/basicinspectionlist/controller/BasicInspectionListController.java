package com.goboosoft.modules.basicinspectionlist.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.basicinspectionlist.entity.BasicInspectionListEntity;
import com.goboosoft.modules.basicinspectionlist.excel.BasicInspectionListExcel;
import com.goboosoft.modules.basicinspectionlist.service.BasicInspectionListService;
import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import com.goboosoft.modules.basicinspectionsteps.entity.BasicInspectionStepsEntity;
import com.goboosoft.modules.basicinspectionsteps.service.BasicInspectionStepsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@RestController
@RequestMapping("basicinspectionlist/basicinspectionlist")
@Api(tags="基础 - 检查项/检查内容信息表")
public class BasicInspectionListController {
    @Autowired
    private BasicInspectionListService basicInspectionListService;
    @Autowired
    private BasicInspectionStepsService basicInspectionStepsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = "level", value = "0检查项 1督查内容", paramType = "query",required = false, dataType="String") ,
    })
    public Result<PageData<BasicInspectionListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BasicInspectionListDTO> page = basicInspectionListService.pageList(params);
        return new Result<PageData<BasicInspectionListDTO>>().ok(page);
    }

    @GetMapping("companyList")
    @ApiOperation("公司检查项的目录")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query",required = false, dataType="String") ,
    })
    public Result<List<BasicInspectionListDTO>> companyList(@ApiIgnore @RequestParam Map<String, Object> params){
        List<BasicInspectionListDTO> list = basicInspectionListService.companyList(params);
        return new Result<List<BasicInspectionListDTO>>().ok(list);
    }


    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<BasicInspectionListDTO> get(@PathVariable("id") Long id){
        BasicInspectionListDTO data = basicInspectionListService.get(id);
        // 添加步骤
        List<BasicInspectionStepsDTO> list = basicInspectionStepsService.getByInspectionListId(id);
        data.setStepsList(list);
        return new Result<BasicInspectionListDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody BasicInspectionListDTO dto){
        //效验数据
        basicInspectionListService.saveDTO(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody BasicInspectionListDTO dto){
        //效验数据
        //ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        basicInspectionListService.updateDTO(dto);
        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        basicInspectionListService.deleteByIds(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BasicInspectionListDTO> list = basicInspectionListService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, BasicInspectionListExcel.class);
    }

}