package com.goboosoft.industry.homepage.controller;


import com.goboosoft.common.utils.Result;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GovernDTO;
import com.goboosoft.industry.govern.service.GovernService;
import com.goboosoft.industry.homepage.dto.EnterprisesTotalDTO;
import com.goboosoft.industry.homepage.dto.InspectDetailsDTO;
import com.goboosoft.industry.homepage.dto.TaskTotalDTO;
import com.goboosoft.industry.homepage.service.EnterprisesTotalService;
import com.goboosoft.industry.multiple.dto.IndustryTaskListDTO;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行业治理
 * @author yuzhao
 */
@RestController
@RequestMapping("/homepage")
@Api(tags="首页显示")
public class HomePageController {
    @Autowired
    private EnterprisesTotalService enterprisesTotalService;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;

    @GetMapping("taskTotal")
    @ApiOperation(value = "首页任务数量统计")
    public Result<TaskTotalDTO> taskTotal(){
        TaskTotalDTO taskTotalDTO = enterprisesTotalService.getTaskTotal();
        return new Result<TaskTotalDTO>().ok(taskTotalDTO);
    }

    @GetMapping("enterprisesTotal")
    @ApiOperation(value = "企业总数统计")
    public Result<EnterprisesTotalDTO> companyCount(){
        EnterprisesTotalDTO enterprisesTotalDTO = enterprisesTotalService.getcompanyCount();
        return new Result<EnterprisesTotalDTO>().ok(enterprisesTotalDTO);
    }

    /**
     * 根据检查项ID查询检查项详情
     * created by yuzhao
     * @return
     */
    @GetMapping("details")
    @ApiOperation("查询检查项详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = true, dataType="int"),
            @ApiImplicitParam(name = "inspectId", value = "检查项id", paramType = "query",required = true, dataType="long")
    })
    public Result<InspectDetailsDTO> get(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<InspectDetailsDTO> list =conmpanyInspectService.getInspectCountDetails(params);
        return new Result().ok(list);
    }
}
