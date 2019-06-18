package com.goboosoft.industry.supervisionandinspection.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionMxDTO;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionMxService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.industry.multiple.service.BusinessManagerService;
import com.goboosoft.industry.multiple.service.IndustryTaskListSelectService;
import com.goboosoft.industry.supervisionandinspection.dto.CorrectionListDTO;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionEntity;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionMxEntity;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionService;
import com.goboosoft.industry.supervisionandinspection.dto.*;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemContentService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.dto.SysCompanyDTO;
import com.goboosoft.system.sys.service.SysCompanysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@RestController
@RequestMapping("busininspectiontask")
@Api(tags = "督导检查")
public class BusinInspectionTaskController {

    @Autowired
    private BusinInspectionTaskService businInspectionTaskService;
    @Autowired
    private IndustryTaskListSelectService industryTaskListSelectService;
    @Autowired
    private BusinessManagerService businessManagerService;

    @GetMapping("getList")
    @ApiOperation("获取督查列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<BusinInspectionTaskListDTO>> getList(@ApiIgnore @RequestParam Map<String, Object> params) {
        params.put("userId", SecurityUser.getUserId());
        List<BusinInspectionTaskListDTO> list = businInspectionTaskService.getList(params);
        for (BusinInspectionTaskListDTO businInspectionTaskListDTO : list) {
            businInspectionTaskListDTO.setScenePhoto(businInspectionTaskService.addPhotoPath(businInspectionTaskListDTO.getScenePhoto()));
        }
        return new Result<List<BusinInspectionTaskListDTO>>().ok(list);
    }

    @GetMapping("getInspectContent")
    @ApiOperation("查看详情时获取检查内容列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inspectionTaskItemId", value = "检查项目Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<PageData<BusinInspectionTaskItemContentDTO>> getInspectContent(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<BusinInspectionTaskItemContentDTO> page = businInspectionTaskService.getInspectContent(params);
        return new Result<PageData<BusinInspectionTaskItemContentDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("查看详情时获取督导内容")
    public Result<InspectionTaskInfoDTO> get(@PathVariable("id") Long id) {

        InspectionTaskInfoDTO inspectionTaskInfo = businInspectionTaskService.getInspectionTaskInfo(id);

        return new Result<InspectionTaskInfoDTO>().ok(inspectionTaskInfo);

    }

    @PostMapping("saveBusinInspectionTask")
    @ApiOperation("保存督查任务")
    @LogOperation("保存督查任务")
    public Result saveBusinInspectionTask(@RequestBody BusinInspectionTaskDTO dto) {

        Long aLong = businInspectionTaskService.saveBusinInspectionTask(dto);

        return new Result().ok(aLong);
    }

    @GetMapping("getCompanyInspect")
    @ApiOperation("获取检查项列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businInspectionTaskId", value = "督导Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<PageData<BusinInspectionTaskItemListDTO>> getCompanyinspect(@ApiIgnore @RequestParam Map<String, Object> params) {

        PageData<BusinInspectionTaskItemListDTO> companyinspect = businInspectionTaskService.getCompanyinspect(params);

        return new Result<PageData<BusinInspectionTaskItemListDTO>>().ok(companyinspect);
    }

    @GetMapping("getCompanyInspectContent")
    @ApiOperation("获取检查内容列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inspectionTaskItemId", value = "检查项目Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<PageData<InspectionTaskItemContentListDTO>> getCompanyInspectContent(@ApiIgnore @RequestParam Map<String, Object> params) {

        PageData<InspectionTaskItemContentListDTO> companyInspectContent = businInspectionTaskService.getCompanyInspectContent(params);

        return new Result<PageData<InspectionTaskItemContentListDTO>>().ok(companyInspectContent);
    }

    @PostMapping("/saveInspectContent")
    @ApiOperation("保存检查内容")
    public Result saveInspectContent(@RequestBody List<InspectionContentDTO> dtos) {

        businInspectionTaskService.saveInspectContent(dtos);

        return new Result();
    }

    @GetMapping("getInspectResult")
    @ApiOperation("检查过程中获取检查结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businInspectionTaskId", value = "督导Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<ResultDTO> getInspectResult(@ApiIgnore @RequestParam Map<String, Object> params) {

        ResultDTO inspectResult = businInspectionTaskService.getInspectResult(params);

        return new Result<ResultDTO>().ok(inspectResult);
    }

    @PutMapping
    @ApiOperation("修改督导状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "督导Id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "scenePhoto", value = "现场照片", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态 0新建/计划 1已拍照 2检查完毕 3已出结果 4已评价 5已完成", paramType = "query", dataType = "String")
    })
    public Result update(@ApiIgnore @RequestBody Map<String, Object> params) {
        Long id = Long.valueOf((String) params.get("id"));
        Integer status = (Integer) params.get("status");
        BusinInspectionTaskDTO businInspectionTaskDTO = businInspectionTaskService.get(id);
        if (status == 1) {
            String scenePhoto = (String) params.get("scenePhoto");
            businInspectionTaskDTO.setScenePhoto(scenePhoto);
            businInspectionTaskDTO.setStatus(1);
        } else if (status == 3) {
            businInspectionTaskDTO.setStatus(3);
        }

        businInspectionTaskService.update(businInspectionTaskDTO);
        return new Result();
    }

    @PostMapping("saveBusinInspectionReport")
    @ApiOperation("保存督查任务评价")
    @LogOperation("保存督查任务评价")
    public Result saveBusinInspectionReport(@RequestBody InspectionTaskReportDTO dto) {

        businInspectionTaskService.saveBusinInspectionReport(dto);

        return new Result();
    }

    @GetMapping("getCorrectionList")
    @ApiOperation("待整改列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<CorrectionListDTO>> getCorrectionList(@ApiIgnore @RequestParam Map<String, Object> params) {

        params.put("userId", SecurityUser.getUserId());
        List<CorrectionListDTO> correctionList = businInspectionTaskService.getCorrectionList(params);
        return new Result<List<CorrectionListDTO>>().ok(correctionList);

    }

    @GetMapping("getCorrectionContent")
    @ApiOperation("获取整改项及内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inspectionCorrectionId", value = "整改Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<List<CorrectionInfoDTO>> getCorrectionContent(@ApiIgnore @RequestParam Map<String, Object> params) {

        Long inspectionCorrectionId = Long.valueOf((String) params.get("inspectionCorrectionId"));
        List<CorrectionInfoDTO> correctionContents = businInspectionTaskService.getCorrectionContent(inspectionCorrectionId, 1);
        return new Result<List<CorrectionInfoDTO>>().ok(correctionContents);

    }

    @GetMapping("getCorrectionContentInfo")
    @ApiOperation("获取整改内容详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "明细Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<CorrectionDTO> getCorrectionContentInfo(@ApiIgnore @RequestParam Map<String, Object> params) {

        Long id = Long.valueOf((String) params.get("id"));
        CorrectionDTO correctionContentInfo = businInspectionTaskService.getCorrectionContentInfo(id);
        correctionContentInfo.setPhotos(businInspectionTaskService.addPhotoPath(correctionContentInfo.getPhotos()));
        correctionContentInfo.setUnderwrite(businInspectionTaskService.addPhotoPath(correctionContentInfo.getUnderwrite()));
        return new Result<CorrectionDTO>().ok(correctionContentInfo);

    }

    @GetMapping("getCheckList")
    @ApiOperation("待复查列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<CheckListDTO>> getCheckList(@ApiIgnore @RequestParam Map<String, Object> params) {

        params.put("userId", SecurityUser.getUserId());
        List<CheckListDTO> checkList = businInspectionTaskService.getCheckList(params);
        return new Result<List<CheckListDTO>>().ok(checkList);

    }

    @GetMapping("getCheckContent")
    @ApiOperation("获取复查项及内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inspectionCorrectionId", value = "整改Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<List<CorrectionInfoDTO>> getCheckContent(@ApiIgnore @RequestParam Map<String, Object> params) {

        Long inspectionCorrectionId = Long.valueOf((String) params.get("inspectionCorrectionId"));
        List<CorrectionInfoDTO> correctionContent = businInspectionTaskService.getCorrectionContent(inspectionCorrectionId, 2);
        return new Result<List<CorrectionInfoDTO>>().ok(correctionContent);

    }

    @GetMapping("getCheckContentInfo")
    @ApiOperation("获取复查内容详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "明细Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<CheckDTO> getCheckContentInfo(@ApiIgnore @RequestParam Map<String, Object> params) {

        Long id = Long.valueOf((String) params.get("id"));
        CheckDTO checkContentInfo = businInspectionTaskService.getCheckContentInfo(id);
        checkContentInfo.setUnderwrite(businInspectionTaskService.addPhotoPath(checkContentInfo.getUnderwrite()));
        checkContentInfo.setFinishPhotos(businInspectionTaskService.addPhotoPath(checkContentInfo.getFinishPhotos()));
        return new Result<CheckDTO>().ok(checkContentInfo);

    }

    @PostMapping("/saveCheckContent")
    @ApiOperation("保存复查内容")
    @LogOperation("保存复查内容")
    public Result saveCheckContent(@RequestBody CheckSaveDTO dto) {

        businInspectionTaskService.saveCheckContent(dto);
        return new Result();
    }

    @GetMapping("getReportList")
    @ApiOperation("报告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "companyId", value = "公司Id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<ReportDTO>> getReportList(@ApiIgnore @RequestParam Map<String, Object> params) {

        params.put("userId", SecurityUser.getUserId());
        List<ReportDTO> reportDTOList = businInspectionTaskService.getReportList(params);
        return new Result<List<ReportDTO>>().ok(reportDTOList);

    }

    @GetMapping("getReportListByCompanyId")
    @ApiOperation("获取公司报告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "companyId", value = "公司Id", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<ReportDTO>> getReportListByCompanyId(@ApiIgnore @RequestParam Map<String, Object> params) {
        String companyId = (String) params.get("companyId");
        if (StringUtils.isEmpty(companyId)) {
            companyId = SecurityUser.getUser().getCompanyId().toString();
            params.put("companyId", companyId);
        }
        List<ReportDTO> reportDTOList = businInspectionTaskService.getReportListByCompanyId(params);
        return new Result<List<ReportDTO>>().ok(reportDTOList);

    }

    @GetMapping("getReportResult")
    @ApiOperation("获取报告检查结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businInspectionTaskId", value = "督导Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<ResultDTO> getReportResult(@ApiIgnore @RequestParam Map<String, Object> params) {
        ResultDTO reportResult = businInspectionTaskService.getReportResult(params);
        return new Result<ResultDTO>().ok(reportResult);
    }

    @GetMapping("getReportInfo")
    @ApiOperation("获取报告详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "明细Id", paramType = "query", required = true, dataType = "int"),
    })
    public Result<ReportInfoDTO> getReportInfo(@ApiIgnore @RequestParam Map<String, Object> params) {

        Long id = Long.valueOf((String) params.get("id"));
        ReportInfoDTO reportInfo = businInspectionTaskService.getReportInfo(id);
        reportInfo.setCheckUnderwrite(businInspectionTaskService.addPhotoPath(reportInfo.getCheckUnderwrite()));
        reportInfo.setInspectUnderwrite(businInspectionTaskService.addPhotoPath(reportInfo.getInspectUnderwrite()));
        reportInfo.setCorrectionUnderwrite(businInspectionTaskService.addPhotoPath(reportInfo.getCorrectionUnderwrite()));
        return new Result<ReportInfoDTO>().ok(reportInfo);

    }

    @GetMapping("getCount")
    @ApiOperation("获取待整改和待审核总数")
    public Result<Map<String, Integer>> getCount() {
        Integer checkCountByUserId = businInspectionTaskService.getCheckCountByUserId();
        Integer correctionCountByUserId = businInspectionTaskService.getCorrectionCountByUserId();
        Map<String, Object> params = new HashMap<>();
        Long userId = SecurityUser.getUserId();
        params.put("userId", userId);
        Integer taskReceiveCount = industryTaskListSelectService.getReceiveListNum(params);
        Integer noApproalCount = businessManagerService.queryNoApproalNumber();
        Map<String, Integer> result = new HashMap<>();
        result.put("checkCount", checkCountByUserId);
        result.put("correctionCount", correctionCountByUserId);
        result.put("taskReceiveCount", taskReceiveCount);
        result.put("noApproalCount", noApproalCount);
        return new Result<Map<String, Integer>>().ok(result);
    }

    @GetMapping("getCompanyGuide")
    @ApiOperation("获取企业指导")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "企业Id", paramType = "query", required = true, dataType = "long"),
    })
    public Result<CompanyGuideDTO> getCompanyGuide(@ApiIgnore @RequestParam Map<String, Object> params) {
        Long companyId = Long.valueOf((String) params.get("companyId"));
        CompanyGuideDTO companyGuideDTO = businInspectionTaskService.getCompanyGuide(companyId);
        return new Result<CompanyGuideDTO>().ok(companyGuideDTO);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        businInspectionTaskService.delete(ids);

        return new Result();
    }
}