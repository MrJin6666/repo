package com.goboosoft.industry.statistics.controller;


import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.statistics.dto.*;
import com.goboosoft.industry.statistics.service.MajorRegulatoryService;
import com.goboosoft.industry.statistics.service.RankingService;
import com.goboosoft.industry.statistics.service.StatisticsService;
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
 * 行业统计
 *
 * @author
 */
@RestController
@RequestMapping("/statistics")
@Api(tags = "行业-统计")
public class ApiStatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private MajorRegulatoryService majorRegulatoryService;
    @Autowired
    private RankingService rankingService;

    @GetMapping("supervisePeopleTotal")
    @ApiOperation(value = "督导人次统计 0.年1.季2.月3.周")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dateType", value = "日期段类型", paramType = "query",required = true, dataType="int")
    })
    public Result<List<SupervisePeopleDTO>> supervisePeople(@ApiIgnore @RequestParam Map<String, Object> params){
          Long userId = SecurityUser.getUserId();
          params.put("userId",userId);
        List<SupervisePeopleDTO> listData = statisticsService.getSupervisePeople(params);
        return new Result<List<SupervisePeopleDTO>>().ok(listData);
    }

    /**
     * 统计-重点监管
     *
     * @author jinxin
     */
    @GetMapping("queryCompanyList")
    @ApiOperation(value = "重点监管企业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "quarter", value = "季度", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "month", value = "月", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "week", value = "周", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType="int"),
    })
    public Result<List<MajorRegulatoryDTO>> queryCompanyList(@ApiIgnore @RequestParam Map<String, Object> params){
        List<MajorRegulatoryDTO> companyList = majorRegulatoryService.queryCompanyList(params);
        return new Result<List<MajorRegulatoryDTO>>().ok(companyList);
    }


    /**
     * 统计-排名
     * @author jinxin
     */
    @GetMapping("queryRanking")
    @ApiOperation(value = "排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "quarter", value = "季度", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "month", value = "月", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "week", value = "周", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType="int"),
    })
    public Result<List<RankingDTO>> queryRanking(@ApiIgnore @RequestParam Map<String, Object> params){
        List<RankingDTO> rankingList = rankingService.queryRanking(params);
        return new Result<List<RankingDTO>>().ok(rankingList);
    }

    /**
     * 统计-常见问题
     *
     * @author yuzhao
     */
    @GetMapping("commonProblems")
    @ApiOperation(value = "常见问题统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "quarter", value = "季度", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "month", value = "月", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "week", value = "周", paramType = "query", dataType="string") ,
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = false, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query",required = false, dataType="int"),
    })
    public Result<List<CommonProblemsDTO>> commonProblems(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<CommonProblemsDTO> problemsDTOS = statisticsService.getProblemsList(params);
        return new Result<List<CommonProblemsDTO>>().ok(problemsDTOS);
    }

}
