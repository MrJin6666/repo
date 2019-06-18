package com.goboosoft.industry.govern.controller;


import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.govern.dto.*;
import com.goboosoft.industry.govern.service.GovernService;
import com.goboosoft.industry.govern.service.GuideCardItemService;
import com.goboosoft.system.security.user.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 行业治理
 * @author jinixin
 */
@RestController
@RequestMapping("/govern")
@Api(tags="行业-治理")
public class ApiGovernController {
    @Autowired
    private GovernService governService;
    @Autowired
    private GuideCardItemService guideCardItemService;
    @GetMapping("queryTodayGovern")
    @ApiOperation(value = "当日治理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", value = "区域", paramType = "query", required = false, dataType="string") ,
            @ApiImplicitParam(name = "governByYouself", value = "我治理", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType="int"),
    })
    public Result<List<GovernDTO>> queryTodayGovern(@ApiIgnore @RequestParam Map<String, Object> params){
        List<GovernDTO> governDTOS = governService.queryCompanyName(params);
        return new Result<List<GovernDTO>>().ok(governDTOS);
    }

    @GetMapping("queryTodayCompanyItem")
    @ApiOperation(value = "根据公司id查询当日治理详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "企业id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query",required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType = "int"),
    })
    public Result<List<GovernItemDTO>>queryTodayCompanyItem(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<GovernItemDTO>todayCompanyList = governService.queryTodayCompanyItem(params);
        return new Result<List<GovernItemDTO>>().ok(todayCompanyList);
    }


    @GetMapping("queryExceedGovern")
    @ApiOperation(value = "超期公司列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", value = "区域", paramType = "query", required = false, dataType="string") ,
            @ApiImplicitParam(name = "governByYouself", value = "我治理", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType="int"),
    })
    public Result<List<ExceedGovernDTO>> queryExceedGovern(@ApiIgnore @RequestParam Map<String, Object> params){
        List<ExceedGovernDTO> governDTOS = governService.queryExceedCompanyName(params);
        return new Result<List<ExceedGovernDTO>>().ok(governDTOS);
    }

    @GetMapping("queryExceedCompanyItem")
    @ApiOperation(value = "根据公司id查询超期详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "企业id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query",required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType = "int"),
    })
    public Result<List<GovernItemDTO>> queryExceedCompanyItem(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<GovernItemDTO>queryExceedCompanyItem = governService.queryExceedCompanyItem(params);
        return new Result<List<GovernItemDTO>>().ok(queryExceedCompanyItem);
    }


    @GetMapping("queryGovernRecordCompanyList")
    @ApiOperation(value = "查询治理档案企业名称列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", value = "区域", paramType = "query", required = false, dataType="string") ,
            @ApiImplicitParam(name = "governByYouself", value = "我治理", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",  required = false, dataType = "int"),
    })
    public Result<List<QueryCompanyListDTO>> queryGovernRecordCompanyList(@ApiIgnore @RequestParam Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<QueryCompanyListDTO> governRecordCompanyList = governService.queryGovernRecordCompanyList(params);
        return new Result<List<QueryCompanyListDTO>>().ok(governRecordCompanyList);
    }


    @GetMapping("queryGuideCardItemByCheckStatus")
    @ApiOperation(value = "根据公司id和检查状态查询指导列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "企业id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "checkStatus", value = "检查状态", paramType = "query", required = true,dataType = "int"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", dataType = "int"),
    })
    public Result<List<GuideCardItemDTO>> queryGuideCardItemBycheckStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<GuideCardItemDTO> guideCardItemDTOS = guideCardItemService.queryGuideCardItemBycheckStatus(params);
        return new Result<List<GuideCardItemDTO>>().ok(guideCardItemDTOS);
    }


    @GetMapping("queryGuideExceedListByStatus")
    @ApiOperation(value = "根据公司id和治理状态查询指导列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "企业id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "治理状态", paramType = "query", required = true,dataType = "int"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", dataType = "int"),
    })
    public Result<List<GuideExceedDTO>> queryGuideExceedListByStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<GuideExceedDTO> guideExceedList = governService.queryGuideExceedListByStatus(params);
        return new Result<List<GuideExceedDTO>>().ok(guideExceedList);
    }

}
