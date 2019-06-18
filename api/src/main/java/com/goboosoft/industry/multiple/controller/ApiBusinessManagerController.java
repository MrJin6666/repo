package com.goboosoft.industry.multiple.controller;

import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.entity.CompanyManageLogEntity;
import com.goboosoft.industry.multiple.dto.BusinessDTO;
import com.goboosoft.industry.multiple.dto.BusinessItemDTO;
import com.goboosoft.industry.multiple.service.BusinessManagerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 企业管理
 *
 * @author jinixin
 */
@RestController
@RequestMapping("/business")
@Api(tags = "行业-企业管理")
public class ApiBusinessManagerController {
    @Autowired
    private BusinessManagerService businessManagerService;

    @GetMapping("queryCompanyByCheckStatus")
    @ApiOperation(value = "根据企业检查状态查询企业列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "checkStatus", value = "检查状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<BusinessDTO>> queryCompanyByCheckStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<BusinessDTO> businessDTOS = businessManagerService.queryCompanyByCheckStatus(params);
        return new Result<List<BusinessDTO>>().ok(businessDTOS);
    }

    @GetMapping("queryCompanyItemByCompanyId")
    @ApiOperation(value = "根据企业id查询企业详情")
    public Result<BusinessItemDTO> queryCompanyItemByCompanyId(@ApiParam(value = "公司id", name = "companyId") @RequestParam String companyId) {
        BusinessItemDTO businessItemDTO = businessManagerService.queryCompanyItemByCompanyId(companyId);
        return new Result<BusinessItemDTO>().ok(businessItemDTO);
    }

    @GetMapping("queryCompanyListByStatus")
    @ApiOperation(value = "根据企业审核状态查询企业列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "审核状态", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<BusinessDTO>> queryCompanyListByStatus(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<BusinessDTO> businessDTOS = businessManagerService.queryCompanyListByStatus(params);
        return new Result<List<BusinessDTO>>().ok(businessDTOS);
    }


    @GetMapping("queryCompanyRecord")
    @ApiOperation(value = "根据企业id查询企业记录")
    public Result<List<CompanyManageLogEntity>> queryCompanyRecord(@ApiParam(value = "企业id", name = "companyId") @RequestParam String companyId) {
        List<CompanyManageLogEntity> companyManageLogDTOS = businessManagerService.queryCompanyRecord(companyId);
        return new Result<List<CompanyManageLogEntity>>().ok( companyManageLogDTOS);
    }


    @GetMapping("queryNoApproalNumber")
    @ApiOperation(value = "查询待审批企业总数")
    public Result<Integer> queryNoApproalNumber() {
        Integer integer = businessManagerService.queryNoApproalNumber();
        return new Result<Integer>().ok(integer);
    }


}
