package com.goboosoft.modules.screen;

import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.Result;
import com.goboosoft.modules.busininspect.service.BusinInspectionCorrectionService;
import com.goboosoft.modules.conmpanyinspect.service.ConmpanyInspectService;
import com.goboosoft.modules.governplan.service.GovernPlanService;
import com.goboosoft.modules.message.dto.SysMailLogDTO;
import com.goboosoft.modules.supervisionandinspection.service.BusinInspectionTaskService;
import com.goboosoft.modules.syscompany.dto.SysCompanyDTO;
import com.goboosoft.modules.syscompany.service.SysCompanyService;
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

import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月19日 9:04
 * version 1.0
 */
@RestController
@RequestMapping("screen/home")
@Api(tags="大屏首页")
public class ScreenHomeController {

    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private BusinInspectionCorrectionService businInspectionCorrectionService;
    @Autowired
    private GovernPlanService governPlanService;
    @Autowired
    private BusinInspectionTaskService businInspectionTaskService;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;


    @GetMapping("companyRank")
    @ApiOperation("企业状态排行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
    })
    public Result<PageData<Map<String,Object>>> companyRank(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<Map<String,Object>> page = sysCompanyService.pageScreenList(params);
        return new Result<PageData<Map<String,Object>>>().ok(page);
    }

    @GetMapping("companyRe")
    @ApiOperation("企业整改完成任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
    })
    public Result<PageData<Map<String,Object>>> companyRe(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put(Constant.ORDER_FIELD,"correction_date");
        params.put(Constant.ORDER,"desc");
        PageData<Map<String,Object>> page = businInspectionCorrectionService.companyScreenRe(params);
        return new Result<PageData<Map<String,Object>>>().ok(page);
    }

    @GetMapping("companyPlan")
    @ApiOperation("企业治理计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
    })
    public Result<PageData<Map<String,Object>>> companyPlan(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put(Constant.ORDER_FIELD,"create_date");
        params.put(Constant.ORDER,"desc");
        PageData<Map<String,Object>> page = governPlanService.screenCompanyPlan(params);
        return new Result<PageData<Map<String,Object>>>().ok(page);
    }

    @GetMapping("companyDd")
    @ApiOperation("行业督导列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
    })
    public Result<PageData<Map<String,Object>>> companyDd(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put(Constant.ORDER_FIELD,"create_date");
        params.put(Constant.ORDER,"desc");
        PageData<Map<String,Object>> page = businInspectionTaskService.screenDd(params);
        return new Result<PageData<Map<String,Object>>>().ok(page);
    }

    @GetMapping("companyInspectionNum")
    @ApiOperation("根据组织架构查询，检查项的数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = "deptId", value = "组织架构", paramType = "query",required = true, dataType="String") ,
    })
    public Result<PageData<Map<String,Object>>> companyInspectionNum(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put(Constant.ORDER_FIELD,"type");
        params.put(Constant.ORDER,"desc");
        PageData<Map<String,Object>> page = conmpanyInspectService.screenCompanyInspectionNum(params);
        return new Result<PageData<Map<String,Object>>>().ok(page);
    }


}
