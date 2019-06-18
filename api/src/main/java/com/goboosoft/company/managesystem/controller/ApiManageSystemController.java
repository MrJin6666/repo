package com.goboosoft.company.managesystem.controller;

import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.manageread.dto.ManageReadDTO;
import com.goboosoft.company.manageread.service.ManageReadService;
import com.goboosoft.company.managesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.company.managesystem.dto.UploadingNoticeDTO;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;
import com.goboosoft.company.managesystem.service.CompanyManageSystemService;
import com.goboosoft.company.managesystem.service.ManageSystemService;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/managesystem")
@Api(tags = "管理制度 -- 企业")
public class ApiManageSystemController {

    @Autowired
    private ManageSystemService manageSystemService;
    @Autowired
    private CompanyManageSystemService companyManageSystemService;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private ManageReadService manageReadService;
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/getSysCompanyNoticeInfo")
    @ApiOperation(value = "获取企业制度列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "isCompany", value = "是否是企业", paramType = "query",required = true, dataType="Integer") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<List<CompanyManageSystemDTO>> getSysCompanyInfo(@ApiIgnore @RequestParam Map<String, Object> params){
        Long companyId = SecurityUser.getUser().getCompanyId();
        SysCompanyDTO sysCompanyDTO = sysCompanyService.get(SecurityUser.getUser().getCompanyId());
        params.put("xgdw",sysCompanyDTO.getXgdw());
        params.put("companyId",companyId);
        List<CompanyManageSystemDTO> sysCompanyInfo ;
        if (sysCompanyDTO != null){

            sysCompanyInfo = manageSystemService.getSysCompanyInfo(params);
        }else {
            return new Result().ok("没有制度信息！");
        }
        return new Result<List<CompanyManageSystemDTO>>().ok(sysCompanyInfo);
    }

    @GetMapping("/getIndustryNoticeInfo")
    @ApiOperation(value = "获取行业制度列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Result<List<CompanyManageSystemDTO>> getIndustryInfo(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<CompanyManageSystemDTO> sysIndustryInfo = manageSystemService.getIndustryInfo(params);
        return new Result<List<CompanyManageSystemDTO>>().ok(sysIndustryInfo);
    }

    @PostMapping("/uploadingNotice")
    @ApiOperation(value = "上传企业通告")
    public Result uploadingNotice(@RequestBody UploadingNoticeDTO dto){
        CompanyManageSystemDTO companyManageSystemDTO = new CompanyManageSystemDTO();
        UserDetail user = SecurityUser.getUser();
        companyManageSystemDTO.setIsCompany(0);
        if(user.getRealName() == null) {
            companyManageSystemDTO.setCreatorName(user.getMobile());
        }else{
            companyManageSystemDTO.setCreatorName(user.getRealName());
        }
        companyManageSystemDTO.setNoticeName(dto.getNoticeName());
        companyManageSystemDTO.setNoticeContent(dto.getNoticeContent());
        companyManageSystemDTO.setAttachment(dto.getAttachment());
        companyManageSystemDTO.setType(Integer.valueOf(dto.getType()));
        companyManageSystemDTO.setState(0);
        Long companyId = SecurityUser.getUser().getCompanyId();
        companyManageSystemDTO.setCompanyId(companyId);
        manageSystemService.save(companyManageSystemDTO);
        return new Result();
    }


    @DeleteMapping("/deleteNotice")
    @ApiOperation(value = "删除通告信息")
    public Result deleteNotice(@RequestBody List<String> ids){
        for(String id : ids) {
            manageSystemService.deleteById(Long.valueOf(id));
        }
        return new Result().ok("success");
    }

    @GetMapping("/getDetail")
    @ApiOperation(value = "获取通告详情")
    @Transactional
    public Result<CompanyManageSystemDTO> getDetail(@RequestParam("id") Long id){
        Result result = new Result();
        try {
            CompanyManageSystemDTO companyManageSystemDTO = manageSystemService.getDetail(id);
            /*ManageReadDTO manageReadDTO = new ManageReadDTO();
            UserDetail user = SecurityUser.getUser();
            manageReadDTO.setFinshRead(user.getId());
            manageReadDTO.setCompanyId(user.getCompanyId());
            manageReadDTO.setFinshReadName(user.getRealName());
            manageReadDTO.setIsCompany(companyManageSystemDTO.getIsCompany());
            manageReadDTO.setManageSystemId(companyManageSystemDTO.getId());
            manageReadDTO.setHeadUrl(user.getHeadUrl());
            manageReadService.deleteManageRead(companyManageSystemDTO.getId(), companyManageSystemDTO.getIsCompany(),user.getId());
            manageReadService.save(manageReadDTO);
            List<ManageReadDTO> list = manageReadService.getLists(companyManageSystemDTO.getId(), companyManageSystemDTO.getIsCompany());*/
            /*if(companyManageSystemDTO.getIsCompany() == 0) {
                //查询企业所有人员
                List<SysUserDTO> userDTOList = sysUserService.getUserCompany(user.getCompanyId());
                companyManageSystemDTO.setUserDTOList(userDTOList);
                result.ok(companyManageSystemDTO);
            }else{
                //查询行业端未查看的所有负责人
                List<SysUserDTO> userDTOList = sysUserService.getUserDept(companyManageSystemDTO.getDeptId());
                for(SysUserDTO sysUserDTO : userDTOList){
                    List<ManageReadDTO> list1 = manageReadService.selectLists(sysUserDTO.getId(), id);
                    if(list1.size() > 0){
                        userDTOList.remove(sysUserDTO);
                    }
                }
                companyManageSystemDTO.setUserDTOList(userDTOList);
            }*/
            //companyManageSystemDTO.setManageReadDTOList(list);
            result.ok(companyManageSystemDTO);
        }catch (Exception e){
            e.printStackTrace();
            result.error("获取失败！");
        }

        return result;
    }


}

