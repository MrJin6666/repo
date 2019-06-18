package com.goboosoft.company.peoplemanagement.controller;

import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.companymenuuser.dto.CompanyMenuDTO;
import com.goboosoft.company.companymenuuser.entity.CompanyUserMenuEntity;
import com.goboosoft.company.companymenuuser.service.CompanyMenuService;
import com.goboosoft.company.companymenuuser.service.CompanyUserMenuService;
import com.goboosoft.company.peoplemanagement.dto.PeopleAddManageDTO;
import com.goboosoft.company.peoplemanagement.dto.PeopleAuthorityDTO;
import com.goboosoft.company.peoplemanagement.dto.PeopleManagementDTO;
import com.goboosoft.company.peoplemanagement.service.PeopleManagementService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import com.goboosoft.system.sys.service.SysDictService;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 企业-人员管理接口
 *  created by yangfl
 *  created time 2019/3/6
 *  class describe
 */
@RestController
@RequestMapping("/peopleManagement")
@Api(tags = "人员管理")
public class ApiPeopleManagementController {

    @Autowired
    private PeopleManagementService peopleManagementService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private CompanyUserMenuService companyUserMenuService;

    @Autowired
    private CompanyMenuService companyMenuService;
    /**
     * 查询联系人-查询 人员管理 列表
     * @return
     */
    @GetMapping("/peopleManagementList")
    @ApiOperation(value = "查询 人员管理 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastRowNum", value = "当前页码，从0开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<List<PeopleManagementDTO>> pagePeopleManageList(@ApiIgnore @RequestParam Map<String, Object> params){

        Long companyId = SecurityUser.getUser().getCompanyId();
        params.put("companyId",companyId);
        List<PeopleManagementDTO> peopleManagementDTOList =  peopleManagementService.peopleList(params);

        return new Result().ok(peopleManagementDTOList);

    }

    @GetMapping("/pageGetPeopleManage")
    @ApiOperation("人员管理列表--分页")
    public Result<PageData<PeopleManagementDTO>> getUserManage(){

        Long companyId = SecurityUser.getUser().getCompanyId();
        PageData<PeopleManagementDTO> page = peopleManagementService.getUserManage(companyId);

        return new Result<PageData<PeopleManagementDTO>>().ok(page);
    }


    @GetMapping("getPeopleAuthority")
    @ApiOperation(value = "获取添加人员权限列表")
    public Result<List<CompanyMenuDTO>> getPeopleAuthority(){

        List<CompanyMenuDTO> menuDTOList = companyMenuService.selectAll();

        return new Result<List<CompanyMenuDTO>>().ok(menuDTOList);
    }

    @PostMapping("/sysUserAddPeople")
    @ApiOperation(value = "企业添加/更新用户")
    @Transactional
    public Result sysUserAddPeople(@RequestBody  PeopleAddManageDTO peopleAddManageDTO){
        Result<SysUserEntity> sysUserEntityResult = null;
        Result result = new Result();
        try {
            if(peopleAddManageDTO.getId()==null) {
                sysUserEntityResult = peopleManagementService.peopleAdd(peopleAddManageDTO);
                result.ok(sysUserEntityResult);
            }else {
                SysUserDTO user=new SysUserDTO();
                user.setId(peopleAddManageDTO.getId());
                user.setRealName(peopleAddManageDTO.getUserName());
                user.setMobile(peopleAddManageDTO.getMobile());
                List<String> manuList = peopleAddManageDTO.getManuList();
                companyUserMenuService.deleteByUserId(user.getId());
                for(String module : manuList){
                    CompanyUserMenuEntity companyUserMenuEntity = new CompanyUserMenuEntity();
                    companyUserMenuEntity.setCompanyMenuModule(module);
                    companyUserMenuEntity.setUserId(user.getId());
                    companyUserMenuService.insert(companyUserMenuEntity);
                }
                sysUserService.update(user);
                result.ok("更新成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.error("添加或者更新异常！");
        }
//        peopleAddManageDTO.setSuperAdmin(Integer.valueOf(params.get("realName")));
        return result;
    }

    @GetMapping("/sysUserAdminShift")
    @ApiOperation(value = "修改权限")
    @Transactional
    public Result sysUserAdminShift(@RequestParam("contactId") Long contactId){
        Result result = new Result();
        UserDetail sysUser = SecurityUser.getUser();
        Integer superAdmin = sysUser.getSuperAdmin();
        try {
            if(superAdmin == 1){
                sysUser.setSuperAdmin(0);
                List<CompanyMenuDTO> companyMenuDTOS = companyMenuService.selectAll();//获取所有权限类型
                SysUserDTO userDTO = sysUserService.get(contactId);//要赋予管理员权限的用户
                userDTO.setSuperAdmin(1);
                companyUserMenuService.deleteByUserId(contactId);
                for (CompanyMenuDTO menuDTO:companyMenuDTOS) {
                    CompanyUserMenuEntity companyUserMenuEntity = new CompanyUserMenuEntity();
                    companyUserMenuEntity.setCompanyMenuModule(menuDTO.getModule());
                    companyUserMenuEntity.setUserId(contactId);
                    companyUserMenuService.insert(companyUserMenuEntity);
                }
                SysUserDTO sysUserDTO2 = ConvertUtils.sourceToTarget(sysUser, SysUserDTO.class);
                sysUserService.update(userDTO);
                sysUserService.update(sysUserDTO2);
                result.ok("管理员权限设置成功");
            }else {
                result.error("非管理员用户不能赋予他人权限");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.error("修改权限异常！");
        }

        return result;
    }



    @GetMapping("/deleteUser")
    @ApiOperation(value = "删除用户")
    @Transactional
    public Result deleteUser(@RequestParam("userId") Long userId){
        Result result = new Result();
        List<String> list = new ArrayList<String>();
        list.add("qygl");
        //list.add("rygl");
        try {
            sysUserService.updateByUserId(userId);
            Integer count = companyUserMenuService.deleteByUserId(userId);
            if(list!=null) {
                for (String module : list) {
                    CompanyUserMenuEntity companyUserMenuEntity = new CompanyUserMenuEntity();
                    companyUserMenuEntity.setCompanyMenuModule(module);
                    companyUserMenuEntity.setUserId(userId);
                    companyUserMenuService.insert(companyUserMenuEntity);
                }
            }
            result.ok("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            result.error("删除失败！");
        }
        return result;
    }

}
