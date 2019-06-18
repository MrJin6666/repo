package com.goboosoft.company.busininspect.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.company.busininspect.dto.*;
import com.goboosoft.company.busininspect.excel.BusinInspectionCorrectionExcel;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionMxService;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionService;
import com.goboosoft.company.busininspect.utils.StatusChangeUtil;
import com.goboosoft.industry.supervisionandinspection.dto.BusinInspectionTaskDTO;
import com.goboosoft.system.security.user.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@RestController
@RequestMapping("busininspect/busininspectioncorrection")
@Api(tags="业务 - 整改基本信息表")
public class BusinInspectionCorrectionController {
    @Autowired
    private BusinInspectionCorrectionService businInspectionCorrectionService;
    @Autowired
    private BusinInspectionCorrectionMxService businInspectionCorrectionMxService;


    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("busininspect:busininspectioncorrection:page")
    public Result<PageData<BusinInspectionCorrectionDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<BusinInspectionCorrectionDTO> page = businInspectionCorrectionService.page(params);

        return new Result<PageData<BusinInspectionCorrectionDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("busininspect:busininspectioncorrection:info")
    public Result<BusinInspectionCorrectionDTO> get(@PathVariable("id") Long id){
        BusinInspectionCorrectionDTO data = businInspectionCorrectionService.get(id);

        return new Result<BusinInspectionCorrectionDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("busininspect:busininspectioncorrection:save")
    public Result save(@RequestBody BusinInspectionCorrectionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        businInspectionCorrectionService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("busininspect:busininspectioncorrection:update")
    public Result update(@RequestBody BusinInspectionCorrectionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        businInspectionCorrectionService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("busininspect:busininspectioncorrection:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        businInspectionCorrectionService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("busininspect:busininspectioncorrection:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<BusinInspectionCorrectionDTO> list = businInspectionCorrectionService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, BusinInspectionCorrectionExcel.class);
    }

    @GetMapping("inspectionCorrectionList")
    @ApiOperation("整改反馈列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "companyId", value = "公司id", paramType = "query", required = false, dataType = "Long"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<InspectionCorrectionListDTO>> inspectionCorrectionList(@ApiIgnore @RequestParam Map<String, Object> params){
        List<InspectionCorrectionListDTO> inspectionCorrectionList =
                businInspectionCorrectionService.getInspectionCorrectionListByCompanyId(params);
        List<InspectionCorrectionListDTO> listDTOS = StatusChangeUtil.statusChangeByDate(inspectionCorrectionList);

        return new Result().ok(listDTOS);
    }

    @GetMapping("inspectionCorrectionDetails")
    @ApiOperation("整改反馈详情")
    public Result<InspectionCorrectionDetailsDTO> inspectionCorrectionDetails(@RequestParam("id") Long id){
        InspectionCorrectionDetailsDTO correctionDetails =
                businInspectionCorrectionService.getInspectionCorrectionDetailsById(id);

        return new Result().ok(correctionDetails);
    }

    @PostMapping("beginCorrection")
    @ApiOperation("开始整改")
    public Result beginCorrection(@RequestBody BeginCorrectionDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        Long id = dto.getId();
        BusinInspectionCorrectionMxDTO correctionMxDTO = businInspectionCorrectionMxService.get(id);
        if (correctionMxDTO != null){
            correctionMxDTO.setFinishRemark(dto.getFinishRemark());
            correctionMxDTO.setFinishPhotos(dto.getFinishPhotos());
            correctionMxDTO.setUnderwrite(dto.getUnderwrite());
            correctionMxDTO.setCorrectionDate(new Date());
            correctionMxDTO.setCorrectionStatus("1");
            businInspectionCorrectionMxService.update(correctionMxDTO);
        }else {
            return new Result().error("没有对应修改数据");
        }
        //获取整改信息
        BusinInspectionCorrectionDTO businInspectionCorrectionDTO =
                businInspectionCorrectionService.get(correctionMxDTO.getInspectionCorrectionId());
        List<Integer> status = businInspectionCorrectionService.getStatus(correctionMxDTO.getInspectionCorrectionId());
        List<Integer> saveStatus = new ArrayList<>();
        for (Integer integer : status) {
            if (integer.equals(0)) {//新建
                break;
            } else {
                saveStatus.add(integer);
            }
        }
        if (saveStatus.size() == status.size()) {

                businInspectionCorrectionDTO.setStatus(1);//待审核
                businInspectionCorrectionDTO.setCorrectionDate(correctionMxDTO.getCorrectionDate());//整改时间
                businInspectionCorrectionDTO.setCorrectionUnderwrite(correctionMxDTO.getUnderwrite());//整改人签字
                businInspectionCorrectionDTO.setCorrectionUserName(SecurityUser.getUser().getRealName());//整改人名称
                businInspectionCorrectionDTO.setCorrectionUserId(SecurityUser.getUser().getId());//整改人id
                businInspectionCorrectionService.update(businInspectionCorrectionDTO);
        }

        return new Result();
    }


}