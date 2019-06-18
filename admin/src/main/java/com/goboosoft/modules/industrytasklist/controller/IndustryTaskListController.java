package com.goboosoft.modules.industrytasklist.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.GenerateOrderUtil;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskListDTO;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskLogDTO;
import com.goboosoft.modules.industrytasklist.entity.IndustryTaskListEntity;
import com.goboosoft.modules.industrytasklist.excel.IndustryTaskListExcel;
import com.goboosoft.modules.industrytasklist.service.IndustryTaskListService;
import com.goboosoft.modules.industrytasklist.service.IndustryTaskLogService;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@RestController
@RequestMapping("industrytasklist/industrytasklist")
@Api(tags="行业 - 新任务发布表")
public class IndustryTaskListController {
    @Autowired
    private IndustryTaskListService industryTaskListService;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<IndustryTaskListDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        // 查询当前用户是否部门
        SysUserDTO sysUserDTO = sysUserService.get(SecurityUser.getUserId());
        // 默认查询普通用户
        if(sysUserDTO.getIsDeptHead() == 0){// 管理用户
            params.put("isDeptHead",0);
        }

        PageData<IndustryTaskListDTO> page = industryTaskListService.pageList(params);
        return new Result<PageData<IndustryTaskListDTO>>().ok(page);
    }

    @GetMapping("pageUser")
    @ApiOperation("用户的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<PageData<IndustryTaskListDTO>> pageUser(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("receiverId", SecurityUser.getUserId());
        PageData<IndustryTaskListDTO> page = industryTaskListService.pageList(params);
        return new Result<PageData<IndustryTaskListDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<IndustryTaskListDTO> get(@PathVariable("id") Long id){
        IndustryTaskListDTO data = industryTaskListService.get(id);

        return new Result<IndustryTaskListDTO>().ok(data);
    }

    @PutMapping("industry")
    @ApiOperation("指派")
    @LogOperation("指派")
    public Result industry(@RequestBody IndustryTaskListDTO dto){
        //效验数据
        industryTaskListService.industry(dto);
        return new Result();
    }


    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody IndustryTaskListDTO dto){
        //效验数据
        industryTaskListService.saveDTO(dto);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody IndustryTaskListDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        industryTaskListService.update(dto);
        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        industryTaskListService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<IndustryTaskListDTO> list = industryTaskListService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, IndustryTaskListExcel.class);
    }

}