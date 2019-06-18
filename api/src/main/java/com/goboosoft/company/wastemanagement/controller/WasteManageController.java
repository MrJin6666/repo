package com.goboosoft.company.wastemanagement.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.exception.RenException;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.company.wastemanagement.dto.*;
import com.goboosoft.company.wastemanagement.entity.WasteManageEntity;
import com.goboosoft.company.wastemanagement.excel.WasteManageExcel;
import com.goboosoft.company.wastemanagement.service.WasteManageService;
import com.goboosoft.company.wastemanagement.service.WasteSelectService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@RestController
@RequestMapping("companywastemanage")
@Api(tags="危废管理")
public class WasteManageController {
    @Autowired
    private WasteManageService wasteManageService;

    @Autowired
    private SysUserService sysUserService;

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
    @RequiresPermissions("wastemanagement:companywastemanage:page")
    public Result<PageData<WasteManageDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<WasteManageDTO> page = wasteManageService.page(params);

        return new Result<PageData<WasteManageDTO>>().ok(page);
    }

    @GetMapping("{creator}")
    @ApiOperation("信息")
//    @RequiresPermissions("wastemanagement:companywastemanage:info")
    public Result<WasteManageDTO> get(@PathVariable("creator") Long creator){
        WasteManageDTO data = wasteManageService.get(creator);

        return new Result<WasteManageDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("wastemanagement:companywastemanage:save")
    public Result save(@RequestBody WasteManageDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wasteManageService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("wastemanagement:companywastemanage:update")
    public Result update(@RequestBody WasteManageDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wasteManageService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("wastemanagement:companywastemanage:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wasteManageService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("wastemanagement:companywastemanage:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WasteManageDTO> list = wasteManageService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, WasteManageExcel.class);
    }

    /**
     * 危废入库添加
     * @param dto
     * @return
     */
    @PostMapping("inboundWasteAddition")
    @ApiOperation("危废入库添加")
    @Transactional
    public Result inboundWasteAddition(@RequestBody WasteManageDTO dto){
        Result result = new Result();

        try {
            //效验数据
            ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
            UserDetail user = SecurityUser.getUser();
            String realName = user.getRealName();
            Long companyId = user.getCompanyId();

            Integer count = wasteManageService.verifyingByTypeAndName(dto.getWasteType(), dto.getWasteName(), companyId);
            if(count == 0) {
                if (realName != null) {
                    dto.setInName(realName);
                }
                dto.setCompanyId(companyId);
                dto.setIsVerify(0);//"是否确认（0：未确认 1：确认）")
                dto.setInboundOrOutbound(0);
                wasteManageService.save(dto);

                wasteSelectService.saveType(dto.getWasteType(), companyId, 1);

                wasteSelectService.saveType(dto.getWasteName(), companyId, 2);

                wasteSelectService.saveType(dto.getUnit(), companyId, 3);

                wasteSelectService.saveType(dto.getWasteSource(), companyId, 4);

                wasteSelectService.saveType(dto.getSaveLocation(), companyId, 5);

                result.ok("保存成功！");
            }else{
                result.error("类型与名称已存在！");
            }
        } catch (RenException e) {
            e.printStackTrace();
            result.ok("保存失败！");
        }

        return result;
    }

    @GetMapping("inboundWasteShowList")
    @ApiOperation("危废入库显示列表")
    public Result<List<InboundWasteListDTO>> inboundWasteShowList(){

        Long companyId = SecurityUser.getUser().getCompanyId();
        Integer inboundOrOutbound = 0;//入库（0：入库 1：出库））
        List<WasteManageEntity> data = wasteManageService.wasteShowList(companyId, inboundOrOutbound);
        List<InboundWasteListDTO> inboundWasteListDTOS = ConvertUtils.sourceToTarget(data, InboundWasteListDTO.class);

        return new Result().ok(inboundWasteListDTOS);
    }

    @GetMapping("inboundWasteShowDetails")
    @ApiOperation("危废入库显示详情")
    public Result<InboundWasteDetailsDTO> inboundWasteShowDetails(@RequestParam Long wasteId){
        WasteManageDTO data = wasteManageService.get(wasteId);
        InboundWasteDetailsDTO inboundWasteDetailsDTO =
                ConvertUtils.sourceToTarget(data, InboundWasteDetailsDTO.class);

        return new Result<InboundWasteDetailsDTO>().ok(inboundWasteDetailsDTO);
    }


    @GetMapping("inboundWasteAdminVerify/{adminSignature}/{id}")
    @ApiOperation("危废入库管理员确认签字")
    @Transactional
    public Result inboundWasteAdminVerify
            (@PathVariable("adminSignature") String adminSignature, @PathVariable("id") Long id){
        Result result = new Result();
        try {
            WasteManageDTO wasteManageDTO = wasteManageService.get(id);
            String realName = SecurityUser.getUser().getRealName();
            wasteManageDTO.setAdmin(realName);
            wasteManageDTO.setAdminSignature(adminSignature);
            wasteManageDTO.setVerifyDate(new Date());
            wasteManageDTO.setIsVerify(1);

            wasteManageService.update(wasteManageDTO);
            result.ok("签字成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.ok("签字失败！");
        }

        return result;
    }


    /**
     * 危废出库添加
     * @param params
     * @return
     */
    @PostMapping("outboundWasteAddition")
    @ApiOperation("危废出库添加")
    public Result outboundWasteAddition(@RequestBody OutboundWasteAdditionGroupDTO params){

        //效验数据
        ValidatorUtils.validateEntity(params, AddGroup.class, DefaultGroup.class);
        String realName = SecurityUser.getUser().getRealName();
        Long companyId = SecurityUser.getUser().getCompanyId();
        List<OutboundWasteAdditionDTO> outboundData = params.getOutboundData();
        WasteManageDTO wasteManageDTO = null;
        for (int i = 0; i < outboundData.size(); i++) {
            wasteManageDTO = new WasteManageDTO();
            OutboundWasteAdditionDTO outbound = outboundData.get(i);

            wasteManageDTO.setWasteType(outbound.getWasteType());
            wasteManageDTO.setWasteName(outbound.getWasteName());
            wasteManageDTO.setCount(outbound.getCount());
            wasteManageDTO.setUnit(outbound.getUnit());
            wasteManageDTO.setReceiverUnit(params.getReceiverUnit());
            wasteManageDTO.setBelongDept(params.getBelongDept());
            wasteManageDTO.setWasteDirection(params.getWasteDirection());
            wasteManageDTO.setOutName(realName);
            wasteManageDTO.setOutboundDate(params.getOutboundDate());
            wasteManageDTO.setOutSignature(params.getOutSignature());
            wasteManageDTO.setUnitSignature(params.getUnitSignature());
            wasteManageDTO.setTransfer(params.getTransfer());
            wasteManageDTO.setCompanyId(companyId);
            wasteManageDTO.setInboundOrOutbound(1);//入库
            wasteManageDTO.setOutboundNum("WFCK"+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+
                    RandomStringUtils.randomNumeric(3));

            wasteManageService.save(wasteManageDTO);
        }
        return new Result();
    }

    @GetMapping("outboundWasteShowList")
    @ApiOperation("危废出库显示列表")
    public Result<List<WasteManageEntity>> outboundWasteShowList(){

        Long companyId = SecurityUser.getUser().getCompanyId();
        Integer inboundOrOutbound = 1;//入库（0：入库 1：出库））
        List<WasteManageEntity> data = wasteManageService.wasteShowList(companyId, inboundOrOutbound);

        return new Result().ok(data);
    }

    @GetMapping("outboundWasteShowDetails")
    @ApiOperation("危废出库显示详情")
    public Result<OutboundWasteDetailsDTO> outboundWasteShowDetails(@RequestParam Long id){

        WasteManageDTO data = wasteManageService.get(id);
        OutboundWasteDetailsDTO outboundWasteDetailsDTO =
                ConvertUtils.sourceToTarget(data, OutboundWasteDetailsDTO.class);
        return new Result<OutboundWasteDetailsDTO>().ok(outboundWasteDetailsDTO);
    }


    @GetMapping("outboundWasteTransfer/{transfer}/{id}")
    @ApiOperation("危废出库关联转移联单")
    public Result outboundWasteTransfer(@PathVariable("transfer") String transfer, @PathVariable("id") Long id){

        WasteManageDTO wasteManageDTO = wasteManageService.get(id);
        wasteManageDTO.setTransfer(transfer);

        wasteManageService.update(wasteManageDTO);

        return new Result();
    }

    @GetMapping("storeManagement")
    @ApiOperation("危废管理库存台账")
    public Result<List<StoreManageDTO>> storeManagement(){

        Long companyId = SecurityUser.getUser().getCompanyId();
        List<WasteManageEntity> wasteManage = wasteManageService.wasteShowList(companyId, null);
        List<StoreManageDTO> storeManageDTOS = ConvertUtils.sourceToTarget(wasteManage, StoreManageDTO.class);
        for (int i = 0; i < storeManageDTOS.size(); i++) {
            if (storeManageDTOS.get(i).getVerifyDate() == null){
                storeManageDTOS.get(i).setVerifyDate(storeManageDTOS.get(i).getCreateDate());
            }
        }

        return new Result().ok(storeManageDTOS);
    }


    @GetMapping("storeManagementSearch/{wasteName}/{startTime}/{endTime}")
    @ApiOperation("危废管理库存台账条件搜索")
    public Result<List<StoreManageDTO>> storeManagementSearch(String wasteName, String startTime,  String endTime){

        Long companyId = SecurityUser.getUser().getCompanyId();
        List<WasteManageEntity> search =
                wasteManageService.storeManageSearch(companyId, wasteName, startTime, endTime);
        List<StoreManageDTO> storeManageDTOS = ConvertUtils.sourceToTarget(search, StoreManageDTO.class);

        return new Result().ok(storeManageDTOS);
    }


    @GetMapping("selectOutboundWasteCount/{wasteType}/{wasteName}")
    @ApiOperation("返回库中还有多少可出库数据")
    public Result selectOutboundWasteCount(@PathVariable("wasteType") String wasteType,
                                           @PathVariable("wasteName") String wasteName){
        Long companyId = SecurityUser.getUser().getCompanyId();
        List<WasteManageEntity> wasteManageEntities =
                wasteManageService.selectOutboundWasteCount(wasteType, wasteName, companyId);
        int wasteCount = wasteManageEntities.size();
        return new Result().ok(wasteCount);
    }

    @GetMapping("verifyingByTypeAndName")
    @ApiOperation("验证类型及名称是否存在")
    public Result verifyingByTypeAndName(@RequestParam("wasteType") String wasteType,@RequestParam("wasteName") String wasteName){
        Long companyId = SecurityUser.getUser().getCompanyId();
        Result result = new Result();
        Integer integer = wasteManageService.verifyingByTypeAndName(wasteType, wasteName, companyId);
        if(integer>0){
            result.error("此类型与名称已存在");
        }
        return result;
    }

    @GetMapping("getDetailsByType")
    @ApiOperation("根据类型获取相应的数据")
    public Result<List<WasteManageDTO>> getDetailsByType(@RequestParam("wasteType") String wasteType){
        Long companyId = SecurityUser.getUser().getCompanyId();
        Result result = new Result();
        List<WasteManageDTO> wasteManageDTOList = wasteManageService.getDetailsByType(wasteType, companyId);

        return new Result<List<WasteManageDTO>>().ok(wasteManageDTOList);
    }

    @PostMapping ("storeOut")
    @ApiOperation("出库添加")
    public Result storeOut(@RequestBody List<WasteManageDTO> manageDTOLists){
        Result result = new Result();
        Long companyId = SecurityUser.getUser().getCompanyId();
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            for(int i = 0;i<manageDTOLists.size()-1;i++){
                for(int j = i+1;j<manageDTOLists.size();j++){
                    WasteManageDTO manageDTO = manageDTOLists.get(i);
                    WasteManageDTO dto = manageDTOLists.get(j);
                    if(manageDTO.getWasteType().contains(dto.getWasteType()) && manageDTO.getWasteName().contains(dto.getWasteName())){
                        Integer num = wasteManageService.isPassByCount(manageDTO.getCount()+dto.getCount(),dto.getWasteName(),dto.getWasteType(),companyId);
                        if(num > 0){
                            return result.error("第"+(j+1)+"的值大于可输入的数量！");
                        }
                    }
                }

            }
            List<WasteManageEntity> lists = wasteManageService.storeOut(manageDTOLists,companyId);
            WasteTypeListsDTO wasteTypeListsDTO = wasteSelectService.getTypes(companyId);
            map.put("lists",lists);
            map.put("wasteTypeListsDTO",wasteTypeListsDTO);
            result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            result.error("保存失败！");
        }
        return result;
    }

    @PostMapping("saveOut")
    @ApiOperation("出库子添加")
    @Transactional
    public Result saveOut(@RequestBody WasteManageDTO dto){
        Result result = new Result();
        Long companyId = SecurityUser.getUser().getCompanyId();
        try {
            for(Long id : dto.getIds()) {
                WasteManageDTO wasteManageDTO = wasteManageService.get(id);
                wasteManageDTO.setReceiverUnit(dto.getReceiverUnit());
                wasteManageDTO.setBelongDept(dto.getBelongDept());
                wasteManageDTO.setWasteDirection(dto.getWasteDirection());
                if(dto.getTransfer()!=null){
                    wasteManageDTO.setTransfer(dto.getTransfer());
                }
                wasteManageDTO.setVerifyDate(new Date());
                wasteManageDTO.setOutName(SecurityUser.getUser().getRealName());
                wasteManageDTO.setOutSignature(dto.getOutSignature());
                wasteManageDTO.setIsVerify(1);
                wasteManageDTO.setUnitSignature(dto.getUnitSignature());
                wasteManageDTO.setOutboundNum("WFCK"+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+
                        RandomStringUtils.randomNumeric(3));
                wasteManageService.update(wasteManageDTO);
                wasteSelectService.saveType(dto.getReceiverUnit(),companyId,6);
                wasteSelectService.saveType(dto.getWasteDirection(),companyId,7);
                wasteSelectService.saveType(dto.getBelongDept(),companyId,8);
                WasteManageDTO dtoOld = wasteManageService.updateCount(wasteManageDTO.getCount(), companyId, wasteManageDTO.getWasteType(), wasteManageDTO.getWasteName(), 0);
                //更新出库表的结存数量
                wasteManageDTO.setBalanceNum(dtoOld.getCount() - wasteManageDTO.getCount());
                wasteManageService.update(wasteManageDTO);
                //结存数量
                dtoOld.setBalanceNum(dtoOld.getCount() - wasteManageDTO.getCount());
                wasteManageService.update(dtoOld);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.error("保存失败！");
        }
        return result;
    }

    @GetMapping("relevance")
    @ApiOperation("关联转移联单")
    @Transactional
    public Result relevance(@RequestParam("id") Long id,@RequestParam("transfer") String transfer){
        Result result = new Result();
        Long companyId = SecurityUser.getUser().getCompanyId();
        try {
            WasteManageDTO wasteManageDTO = wasteManageService.get(id);
            wasteManageDTO.setTransfer(transfer);
            wasteManageService.update(wasteManageDTO);

            result.ok("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.error("更新失败！");
        }
        return result;
    }

    @PostMapping("getTypeAndName")
    @ApiOperation("根据公司id获取类型及名称")
    public Result getTypeAndName(){
        List<WasteManageEntity> wasteManageEntities = new ArrayList<WasteManageEntity>();
        Result result = new Result();
        Long companyId = SecurityUser.getUser().getCompanyId();
        try {
            QueryWrapper<WasteManageEntity> wrapper = new QueryWrapper<WasteManageEntity>();
            wrapper.eq("company_id",companyId);
            wrapper.eq("inbound_or_outbound",0);
            List<WasteManageEntity> lists = wasteManageService.selectByCompanyId(wrapper);
            if(lists.size()>0) {
                for (WasteManageEntity wasteManageEntity : lists) {
                    if(wasteManageEntity.getBalanceNum()!=null){
                        wasteManageEntity.setCount(wasteManageEntity.getBalanceNum());
                    }
                }
            }

            result.ok(lists);
        } catch (Exception e) {
            e.printStackTrace();
            result.error("更新失败！");
        }
        return result;
    }

}