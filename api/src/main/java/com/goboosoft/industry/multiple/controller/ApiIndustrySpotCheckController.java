package com.goboosoft.industry.multiple.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.multiple.dto.*;
import com.goboosoft.industry.multiple.service.IndustryTaskListService;
import com.goboosoft.industry.multiple.service.SpotCheckService;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.security.user.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static com.goboosoft.system.security.user.SecurityUser.getUserId;


/**
 * 行业 -企业抽查
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@RestController
@RequestMapping("demo/industrySpotCheck")
@Api(tags="行业 - 企业抽查")
public class ApiIndustrySpotCheckController {
    @Autowired
    private SpotCheckService spotCheckService;
    @Autowired
    private IndustryTaskListService industryTaskListService;
    @Autowired
    private SysCompanyService sysCompanyService;

    /**
     * 企业抽查样本建立
     * @return
     * @throws IOException
     */
    @PostMapping("create")
    @ApiOperation("企业抽查样本建立")
    public Result save( @RequestBody JSONObject jsonObject/*@RequestBody  List<SpotCheckCompanyDTO> spotCheckCompanyDtoList,@RequestBody List<InspectionProjectDTO> inspectionProjectDtoList*/)throws IOException {
//        JSONObject jsonObject =  JSON.parseObject(jsonString);
//        List<Map<String,Object>> spotCheckCompanyDtoList = (  List<Map<String,Object>>)jsonObject.get("spotCheckCompanyDtoList");
//        List<InspectionProjectDTO> inspectionProjectDtoList = (List<InspectionProjectDTO>)jsonObject.get("inspectionProjectDtoList");
        spotCheckService.saveSample(jsonObject);
        return new Result();
    }
    /**
     * 选择检查项
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("inspectionProject")
    @ApiOperation("选择检查项")
    public Result<InspectionProjectDTO> getCheckItem(){
        List<InspectionProjectDTO> dataList = spotCheckService.getinspectionProject();
        return new Result().ok(dataList);
    }
    /**
     *根据行政区域查询下面所有公司
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("companySelcet")
    @ApiOperation("监管部门id查询下面所有公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int"),
            @ApiImplicitParam(name = "deptId", value = "部门id", paramType = "query",required = true, dataType="String")
    })
    public Result<SpotCheckCompanyDTO> getCompanys(@ApiIgnore @RequestParam Map<String, Object> params){
       /* Map<String,Long> params = new HashedMap();
        Long deptId1 =Long.parseLong(deptId);
        params.put("deptId",deptId1);*/
        Long userId =SecurityUser.getUserId();
        params.put("userId",userId);
        List<SpotCheckCompanyDTO> companysList =sysCompanyService.getCompanys(params);
        return new Result().ok(companysList);
    }
    /**
     * 查询未上报
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getNotReport")
    @ApiOperation("查询未上报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int")
    })
    public Result<SpotCheckSampleListDTO> getNotReportedList(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId= getUserId();
        params.put("userId",userId);
        List<SpotCheckSampleListDTO> dataList = spotCheckService.getNotReportedList(params);
        return new Result().ok(dataList);
    }
    /**
     * 查询已上报
     * created by yuzhao
     * @param
     * @return
     */
    @GetMapping("getReported")
    @ApiOperation("查询已上报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int")
    })
    public Result<SpotCheckSampleListDTO> getReportedList(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId= getUserId();
        params.put("userId",userId);
        List<SpotCheckSampleListDTO> dataList = spotCheckService.getReportedList(params);
        return new Result().ok(dataList);
    }
    /**
     * 根据id查询任务详情
     * created by yuzhao
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查询抽查任务详情")
    public Result<SpotCheckSampleListDTO> get(@PathVariable("id") Long id){
        SpotCheckSampleListDTO data = spotCheckService.getSpotCheckDetails(id);

        return new Result<SpotCheckSampleListDTO>().ok(data);
    }

    /**
     * 根据企业id查询抽查清单
     * @param params
     * @return
     */
    @GetMapping("spotCheckList")
    @ApiOperation("根据企业id查询抽查清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType="int"),
            @ApiImplicitParam(name = "companyId", value = "部门id", paramType = "query",required = true, dataType="Long")
    })
    public Result<SpotCheckListDTO> getspotCheckList(@ApiIgnore @RequestParam Map<String, Object> params){

        List<SpotCheckListDTO> spotCheckList =spotCheckService.getspotCheckList(params);
        return new Result().ok(spotCheckList);
    }
}