package com.goboosoft.company.govern.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.goboosoft.company.busininspect.utils.PhotoJoint;
import com.goboosoft.company.govern.dto.GovernPlanDTO;
import com.goboosoft.company.govern.dto.GovernProcessPlanDTO;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.company.govern.entity.GovernProcessPlanEntity;
import com.goboosoft.company.govern.excel.GovernPlanExcel;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.company.govern.service.GovernProcessPlanService;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.service.BasicInspectionListService;
import com.goboosoft.system.security.user.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@RestController
@RequestMapping("governplan")
@Api(tags="治理计划")
public class GovernPlanController {
    @Autowired
    private GovernPlanService governPlanService;
    @Autowired
    private GovernProcessPlanService governProcessPlanService;
    @Autowired
    private PhotoJoint photoJoint;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:governplan:page")
    public Result<PageData<GovernPlanDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<GovernPlanDTO> page = governPlanService.page(params);

        return new Result<PageData<GovernPlanDTO>>().ok(page);
    }

    @GetMapping("/getGorvenPlanLists")
    @ApiOperation("获取计划列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query",required = false, dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<GovernPlanDTO>> getGorvenPlanLists(@ApiIgnore @RequestParam Map<String, Object> params){
        List<GovernPlanDTO> lists = governPlanService.getGovernPlan(SecurityUser.getUserId(),params);
        //排除已完成之外的
        for (GovernPlanDTO governPlanDTO : lists) {
            if(!(Constant.GovernService.COMPLETE.value()).equals(governPlanDTO.getStatus())) {
                Date durrentDate = new Date();
                if (governPlanDTO.getEndDate().getTime() < durrentDate.getTime()) {
                    governPlanDTO.setOldStstus(governPlanDTO.getStatus());
                    governPlanDTO.setStatus(Constant.GovernService.EXCE.value());     //设置超期
                    governPlanService.update(governPlanDTO);
                }
            }
        }

        return new Result<List<GovernPlanDTO>>().ok(lists);
    }

    @GetMapping("/getGorvenPlanById")
    @ApiOperation("获取计划详情")
    public Result<GovernPlanDTO> getGorvenPlanById(@RequestParam(value = "id",required = true) Long id){
        GovernPlanDTO governPlanDTO = governPlanService.getGorvenPlanById(id);
        return new Result<GovernPlanDTO>().ok(governPlanDTO);
    }

    @PostMapping("/addGorvenPlanPicture")
    @ApiOperation("添加治理照片")
    public Result<GovernPlanDTO> addGorvenPlanPicture(@RequestBody GovernProcessPlanDTO governProcessPlanDTO) {
        String message = null;
        Result result = new Result();
        try {
            governProcessPlanService.save(governProcessPlanDTO);
            result.ok("保存成功！");
        }catch (Exception e){
            e.printStackTrace();
            result.error("保存失败");
        }

        return result;
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:governplan:save")
    public Result save(@RequestBody GovernPlanDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        governPlanService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:governplan:update")
    public Result update(@RequestBody GovernPlanDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        governPlanService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:governplan:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        governPlanService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("demo:governplan:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<GovernPlanDTO> list = governPlanService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, GovernPlanExcel.class);
    }

    @GetMapping("getGovernLists")
    @ApiOperation("获取治理档案")
    @LogOperation("获取治理档案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query",required = false, dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query",required = false, dataType = "Long"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<GovernPlanDTO>> getGovernLists(@ApiIgnore @RequestParam Map<String, Object> params) throws Exception {
        params.put("userId",SecurityUser.getUserId());
        List<GovernPlanDTO> governLists = governPlanService.getGovernLists(params);

        return new Result<List<GovernPlanDTO>>().ok(governLists);
    }

    @GetMapping("getGovernListsByFile")
    @ApiOperation("获取治理档案文件夹")
    @LogOperation("获取治理档案文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "id", value = "治理文件夹id", paramType = "query",required = true, dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query",required = false, dataType = "Long"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<GovernPlanDTO>> getGovernListsByFile(@ApiIgnore @RequestParam Map<String, Object> params) throws Exception {

        List<GovernPlanDTO> governLists = governPlanService.getGovernListsByFile(params);

        return new Result<List<GovernPlanDTO>>().ok(governLists);
    }

    @GetMapping("getGovernDeatils")
    @ApiOperation("获取治理档案详情")
    @LogOperation("获取治理档案详情")
    public Result<GovernPlanDTO> getGovernDeatils(@RequestParam("id") Long id) throws Exception {

        GovernPlanDTO governPlanDTO = governPlanService.get(id);
        Map<String, Object> processPlan = governProcessPlanService.getProcessPlan(governPlanDTO.getId());
        if(processPlan!=null) {
            if (processPlan.get("pictureBefore") != null) {
                governPlanDTO.setPictureBefore((String) processPlan.get("pictureBefore"));
                governPlanDTO.setPersonBefore((String) processPlan.get("personBefore"));
                governPlanDTO.setDateBefore((Date) processPlan.get("dateBefore"));
            }
            if(processPlan.get("pictureProcess") != null){
                governPlanDTO.setPictureProcess((String)processPlan.get("pictureProcess"));
                governPlanDTO.setPersonProcess((String) processPlan.get("personProcess"));
                governPlanDTO.setDateProcess((Date) processPlan.get("dateProcess"));
                governPlanDTO.setDescribProcess((String) processPlan.get("describProcess"));
            }
            if (processPlan.get("pictureAfter") != null) {
                governPlanDTO.setPictureAfter((String) processPlan.get("pictureAfter"));
                governPlanDTO.setPersonAfter((String) processPlan.get("personAfter"));
                governPlanDTO.setDateAfter((Date) processPlan.get("dateAfter"));
                governPlanDTO.setDescribAfter((String) processPlan.get("describAfter"));
            }
            if(processPlan.get("sign") != null){
                governPlanDTO.setSign((String)processPlan.get("sign"));
                governPlanDTO.setPersonFinish((String) processPlan.get("personFinish"));
                governPlanDTO.setDateFinish((Date) processPlan.get("dateFinish"));
            }

            if(processPlan.get("finishDate") != null){
                governPlanDTO.setFinishDate((Date)processPlan.get("finishDate"));
            }
        }
        return new Result<GovernPlanDTO>().ok(governPlanDTO);
    }

}