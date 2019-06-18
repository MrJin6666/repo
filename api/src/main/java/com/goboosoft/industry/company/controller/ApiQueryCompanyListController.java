package com.goboosoft.industry.company.controller;

import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.company.service.QueryCompanyListService;
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
 * 获取公司列表（带权限）
 *
 * @author jinxin
 */
@RestController
@RequestMapping("/query")
@Api(tags = "获取当前登录人所涉及企业列表（带权限）")
public class ApiQueryCompanyListController {
    @Autowired
    private QueryCompanyListService queryCompanyListService;

    @GetMapping("queryCompanyList")
    @ApiOperation(value = "获取当前登录人所涉及企业名称列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query",required = false,  dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = false , dataType = "int"),
    })
    public Result<List<QueryCompanyListDTO>> queryCompanyList(@ApiIgnore @RequestParam Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<QueryCompanyListDTO> companyList = queryCompanyListService.queryCompanyList(params);
        return new Result<List<QueryCompanyListDTO>>().ok(companyList);
    }

    @GetMapping("queryCompanyListByStatus")
    @ApiOperation(value = "根据企业状态查询企业名称列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "企业状态", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
    })
    public Result<List<QueryCompanyListDTO>> queryCompanyListByStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<QueryCompanyListDTO> companyListByStatus = queryCompanyListService.queryCompanyListByStatus(params);
        return new Result<List<QueryCompanyListDTO>>().ok(companyListByStatus);
    }

    @GetMapping("queryCompanyListByCheckStatus")
    @ApiOperation(value = "根据检查状态查询企业名称列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkStatus", value = "企业审核状态", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
    })
    public Result<List<QueryCompanyListDTO>> queryCompanyListByCheckStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<QueryCompanyListDTO> companyListByCheckStatus = queryCompanyListService.queryCompanyListByCheckStatus(params);
        return new Result<List<QueryCompanyListDTO>>().ok(companyListByCheckStatus);
    }

    @GetMapping("queryCompanyListByGovernStatus")
    @ApiOperation(value = "根据治理状态查询企业名称列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "governStatus", value = "治理状态", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
    })
    public Result<List<QueryCompanyListDTO>> queryCompanyListByGovernStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<QueryCompanyListDTO> companyListByGovernStatus = queryCompanyListService.queryCompanyListByGovernStatus(params);
        return new Result<List<QueryCompanyListDTO>>().ok(companyListByGovernStatus);
    }





}
