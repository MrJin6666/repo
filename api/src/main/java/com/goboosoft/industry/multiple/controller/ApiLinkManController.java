package com.goboosoft.industry.multiple.controller;

import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.multiple.dto.*;
import com.goboosoft.industry.multiple.service.*;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联系人
 * @author jinxin
 * created time 2019/3/7
 */
@RestController
@RequestMapping("/linkMan")
@Api(tags="行业-联系人")
public class ApiLinkManController {
    @Autowired
    private LinkManService linkManService;

    @Autowired
    private AddLinkManToSysUserService addLinkManToSysUserService;

    @Autowired
    private SysDeptItemService sysDeptItemService;

    @GetMapping("querySysCompanyAndSysDept")
    @ApiOperation(value = "查询本人所在公司部门及下级公司所有部门")
    public Result<List<QueryDeptAndChildDeptDTO>> querySysCompanyAndSysDept(){
        Long deptId = SecurityUser.getDeptId();
        List<QueryDeptAndChildDeptDTO> list = linkManService.queryDeptAndChildDept(deptId);
        return new Result<List<QueryDeptAndChildDeptDTO>>().ok(list);
    }

    /**
     * 查询本人所在带企公司及下级所有带企公司
     * yuzhao
     * @return
     */
    @GetMapping("querySysCompanyDept")
    @ApiOperation(value = "查询本人所在部门及下级所有部门(带企)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门id", paramType = "query", required = false, dataType="Long")
    })
    public Result<List<QueryDeptAndChildDeptDTO>> querySysCompanyDept(@ApiIgnore @RequestParam Map<String, Object> params){
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<QueryDeptAndChildDeptDTO> list = linkManService.querySysCompanyDept(params);
        return new Result<List<QueryDeptAndChildDeptDTO>>().ok(list);
    }

    /**
     * 根据部门id查询同级部门及下级部门列表(除去分公司)
     * @return
     */
    @GetMapping("getSysDeptAndSameLevelListBySysDeptId")
    @ApiOperation(value = "通过部门id查询同级部门及下级部门列表(除去分公司)")
    public Result<List<SysSameLevelDeptDTO>>getSysDeptAndSameLevelListBySysDeptId(@ApiParam(value = "部门id", name = "id") @RequestParam Long id){
        List<SysSameLevelDeptDTO> list = sysDeptItemService.getSysDeptAndSameLevelListBySysDeptId(id);
        return new Result<List<SysSameLevelDeptDTO>>().ok(list);
    }

    /**
     * 根据部门id查询部门下的下一级列表(除去分公司)
     * @return
     */
    @GetMapping("getSysDeptNextLevelListBySysDeptId")
    @ApiOperation(value = "根据部门id查询部门下的下一级列表(除去分公司)")
    public Result<List<SysSameLevelDeptDTO>>getSysDeptNextLevelListBySysDeptId(@ApiParam(value = "部门id", name = "id") @RequestParam Long id){
        List<SysSameLevelDeptDTO> list = sysDeptItemService.getSysDeptNextLevelListBySysDeptId(id);
        return new Result<List<SysSameLevelDeptDTO>>().ok(list);
    }



    /**
     * 根据部门id查询部门详情列表
     * @return
     */
    @GetMapping("getSysDeptItemListBySysDeptId")
    @ApiOperation(value = "通过部门id查询部门详情列表")
    public Result<List<SysDeptItemDTO>>getSysDeptItemListBySysDeptId(@ApiParam(value = "部门id", name = "id") @RequestParam Long id){
        List<SysDeptItemDTO> list = sysDeptItemService.getSysDeptItemListBySysDeptId(id);
        return new Result<List<SysDeptItemDTO>>().ok(list);
    }

    /**
     * 添加联系人到数据库
     * @param addLinkManDTO
     * @return
     */
    @PostMapping("addLinkManToSysUser")
    @ApiOperation(value = "添加联系人到数据库")
    public Result<SysUserEntity> addLinkManToSysUser(@RequestBody AddLinkManDTO addLinkManDTO){
        Result<SysUserEntity> sysUserEntityResult = addLinkManToSysUserService.addLinkManToSysUser(addLinkManDTO);
        return sysUserEntityResult;
    }

    /**
     * 修改人员到数据库
     * @param updateUserDTO
     * @return
     */
    @PutMapping("updateUserToSysUser")
    @ApiOperation(value = "人员修改")
    public Result<SysUserDTO> updateUserToSysUser(@RequestBody UpdateUserDTO updateUserDTO){
       Result<SysUserDTO> sysUserEntityResult = addLinkManToSysUserService.updateUserToSysUser(updateUserDTO);
        return sysUserEntityResult;
    }


}
