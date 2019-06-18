package com.goboosoft.company.wastemanagement.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.company.wastemanagement.dto.*;
import com.goboosoft.company.wastemanagement.entity.WasteTypeEntity;
import com.goboosoft.company.wastemanagement.service.WasteSelectService;
import com.goboosoft.company.wastemanagement.service.WasteTypeService;
import com.goboosoft.company.wastemanagement.utils.WasteManageUtil;
import com.goboosoft.system.security.user.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


/**
 * 企业危废管理内容类型
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@RestController
@RequestMapping("companywastetype")
@Api(tags="企业危废管理内容类型")
public class WasteTypeController {
    @Autowired
    private WasteTypeService wasteTypeService;
    @Autowired
    private WasteSelectService wasteSelectService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("wastemanagement:companywastetype:page")
    public Result<PageData<WasteTypeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<WasteTypeDTO> page = wasteTypeService.page(params);

        return new Result<PageData<WasteTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("wastemanagement:companywastetype:info")
    public Result<WasteTypeDTO> get(@PathVariable("id") Long id){
        WasteTypeDTO data = wasteTypeService.get(id);

        return new Result<WasteTypeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("wastemanagement:companywastetype:save")
    public Result save(@RequestBody WasteTypeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wasteTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("wastemanagement:companywastetype:update")
    public Result update(@RequestBody WasteTypeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wasteTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("wastemanagement:companywastetype:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wasteTypeService.delete(ids);

        return new Result();
    }


    /**
     * 危废管理入 库内容添加
     * @param dto
     * @return
     */
    @PostMapping("InboundWasteTypeAddition")
    @ApiOperation("危废管理入库内容类型添加")
    public Result InboundWasteTypeAddition(@RequestBody InWasteTypeAdditionDTO dto){

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        Long companyId = SecurityUser.getUser().getCompanyId();
        WasteTypeEntity typeEntity = wasteTypeService.wasteContentTypeList(companyId);

        if (typeEntity != null){
            WasteTypeDTO wasteTypeDTO = WasteManageUtil.inSpliceWasteType(dto, typeEntity);
            wasteTypeDTO.setCompanyId(companyId);
            wasteTypeService.inUpdateWasteType(wasteTypeDTO);
        }else {
            WasteTypeDTO wasteTypeDTO = ConvertUtils.sourceToTarget(dto, WasteTypeDTO.class);
            wasteTypeDTO.setCompanyId(companyId);
            wasteTypeService.save(wasteTypeDTO);
        }

        return new Result();
    }

    @PostMapping("OutboundWasteTypeAddition")
    @ApiOperation("危废管理出库内容类型添加")
    public Result OutboundWasteTypeAddition(@RequestBody OutWasteTypeAdditionDTO dto){

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        Long companyId = SecurityUser.getUser().getCompanyId();
        WasteTypeEntity typeEntity = wasteTypeService.wasteContentTypeList(companyId);

        if (typeEntity != null){
            WasteTypeDTO wasteTypeDTO = WasteManageUtil.outSpliceWasteType(dto, typeEntity);
            wasteTypeDTO.setCompanyId(companyId);
            wasteTypeService.outUpdateWasteType(wasteTypeDTO);
//            wasteTypeService.update(wasteTypeDTO);
        }else {
            WasteTypeDTO wasteTypeDTO = ConvertUtils.sourceToTarget(dto, WasteTypeDTO.class);
            wasteTypeDTO.setCompanyId(companyId);
            wasteTypeService.save(wasteTypeDTO);
        }

        return new Result();
    }

    @GetMapping("wasteContentTypeList")
    @ApiOperation("危废内容列表")
    public Result<WasteTypeResultDTO> wasteContentTypeList(){

        Long companyId = SecurityUser.getUser().getCompanyId();
        WasteTypeEntity typeEntity = wasteTypeService.wasteContentTypeList(companyId);
        WasteTypeDTO typeDTO = ConvertUtils.sourceToTarget(typeEntity, WasteTypeDTO.class);
        WasteTypeResultDTO typeResultDTO = WasteManageUtil.wasteContentTypeList(typeDTO);

        return new Result().ok(typeResultDTO);
    }

    @GetMapping("getTypes")
    @ApiOperation("危废列表")
    public Result<WasteTypeListsDTO> getTypes(){
        Long companyId = SecurityUser.getUser().getCompanyId();
        WasteTypeListsDTO wasteTypeListsDTO = wasteSelectService.getTypes(companyId);
        return new Result<WasteTypeListsDTO>().ok(wasteTypeListsDTO);
    }

}