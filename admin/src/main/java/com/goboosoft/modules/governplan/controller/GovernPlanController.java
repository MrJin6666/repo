package com.goboosoft.modules.governplan.controller;

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
import com.goboosoft.modules.governplan.dto.GovernPlanDTO;
import com.goboosoft.modules.governplan.excel.GovernPlanExcel;
import com.goboosoft.modules.governplan.service.GovernPlanService;
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
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@RestController
@RequestMapping("governplan/governplan")
@Api(tags="治理计划")
public class GovernPlanController {
    @Autowired
    private GovernPlanService governPlanService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = "inspectionListId", value = "治理分类id", paramType = "query",required = false, dataType="String") ,
        @ApiImplicitParam(name = "status", value = "状态", paramType = "query",required = false, dataType="String") ,
        @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query",required = false, dataType="String") ,
        @ApiImplicitParam(name = "name", value = "名称", paramType = "query",required = false, dataType="String") ,
    })
    public Result<PageData<GovernPlanDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<GovernPlanDTO> page = governPlanService.pageList(params);
        return new Result<PageData<GovernPlanDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("governplan:governplan:info")
    public Result<GovernPlanDTO> get(@PathVariable("id") Long id){
        GovernPlanDTO data = governPlanService.get(id);

        return new Result<GovernPlanDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("governplan:governplan:save")
    public Result save(@RequestBody GovernPlanDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        governPlanService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("governplan:governplan:update")
    public Result update(@RequestBody GovernPlanDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        governPlanService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("governplan:governplan:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        governPlanService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("governplan:governplan:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<GovernPlanDTO> list = governPlanService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, GovernPlanExcel.class);
    }

}