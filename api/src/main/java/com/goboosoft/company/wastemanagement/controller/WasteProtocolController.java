package com.goboosoft.company.wastemanagement.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.company.wastemanagement.dto.WasteProtocolAdditionDTO;
import com.goboosoft.company.wastemanagement.dto.WasteProtocolDTO;
import com.goboosoft.company.wastemanagement.dto.WasteProtocolListDTO;
import com.goboosoft.company.wastemanagement.excel.WasteProtocolExcel;
import com.goboosoft.company.wastemanagement.service.WasteProtocolService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@RestController
@RequestMapping("wastemanagement/companywasteprotocol")
@Api(tags="企业危废管理协议")
public class WasteProtocolController {
    @Autowired
    private WasteProtocolService wasteProtocolService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("wastemanagement:companywasteprotocol:page")
    public Result<PageData<WasteProtocolDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<WasteProtocolDTO> page = wasteProtocolService.page(params);

        return new Result<PageData<WasteProtocolDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("wastemanagement:companywasteprotocol:info")
    public Result<WasteProtocolDTO> get(@PathVariable("id") Long id){
        WasteProtocolDTO data = wasteProtocolService.get(id);

        return new Result<WasteProtocolDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("wastemanagement:companywasteprotocol:save")
    public Result save(@RequestBody WasteProtocolDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wasteProtocolService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("wastemanagement:companywasteprotocol:update")
    public Result update(@RequestBody WasteProtocolDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wasteProtocolService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("wastemanagement:companywasteprotocol:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wasteProtocolService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("wastemanagement:companywasteprotocol:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WasteProtocolDTO> list = wasteProtocolService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, WasteProtocolExcel.class);
    }


    /**
     * 危废入库添加
     * @param dto
     * @return
     */
    @PostMapping("wasteProtocolAddition")
    @ApiOperation("危废协议添加")
    public Result wasteProtocolAddition(@RequestBody WasteProtocolAdditionDTO dto){

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        Long companyId = SecurityUser.getUser().getCompanyId();
        String lastDate = wasteProtocolService.wasteProtocolLastTime(companyId);
        if (lastDate != null){//数据库已有数据（不是首次添加）
            String startDate = dto.getStartDate();
            Date date1 = DateUtils.parse(lastDate, "yyyy-MM-dd");
            Date date2 = DateUtils.parse(startDate, "yyyy-MM-dd");
            if (date1.before(date2)){
                System.out.println("保存协议");
                WasteProtocolDTO wasteProtocol = ConvertUtils.sourceToTarget(dto, WasteProtocolDTO.class);
                wasteProtocol.setCompanyId(companyId);
                wasteProtocolService.save(wasteProtocol);
            }else {
                return new Result().error("协议开始时间不能早于上次协议到期时间!");
            }
        }else {//首次添加  不做判断
            System.out.println("保存协议2");
            WasteProtocolDTO wasteProtocol = ConvertUtils.sourceToTarget(dto, WasteProtocolDTO.class);
            wasteProtocol.setCompanyId(companyId);
            wasteProtocolService.save(wasteProtocol);
        }


        return new Result();
    }


    @GetMapping("wasteProtocolShowList")
    @ApiOperation("危废协议显示列表")
    public Result<List<WasteProtocolListDTO>> wasteProtocolShowList(){

        Long companyId = SecurityUser.getUser().getCompanyId();
        List<WasteProtocolDTO> dtoList = wasteProtocolService.protocolShowList(companyId);
        List<WasteProtocolListDTO> listDTOS = ConvertUtils.sourceToTarget(dtoList, WasteProtocolListDTO.class);
        List<WasteProtocolListDTO> overdue = WasteManageUtil.isOverdue(listDTOS);//协议状态（0：正常，1：过期）
        return new Result().ok(overdue);
    }

    @GetMapping("wasteProtocolShowDetails/{protocolId}")
    @ApiOperation("危废协议显示详情")
    public Result<WasteProtocolDTO> wasteProtocolShowDetails(@PathVariable("protocolId") Long protocolId){
        WasteProtocolDTO protocolDTO = wasteProtocolService.get(protocolId);

        return new Result<WasteProtocolDTO>().ok(protocolDTO);
    }


    @GetMapping("wasteProtocolLastTime")
    @ApiOperation("上次危废协议最后期限")
    public Result wasteProtocolLastTime(){
        Long companyId = SecurityUser.getUser().getCompanyId();
        String lastTime = wasteProtocolService.wasteProtocolLastTime(companyId);

        return new Result().ok(lastTime);
    }

}