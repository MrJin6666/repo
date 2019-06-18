package com.goboosoft.modules.syscompany.controller;

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
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogOneDTO;
import com.goboosoft.modules.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.modules.conmpanyinspect.dto.ConmpanyInspectDTO;
import com.goboosoft.modules.conmpanyinspect.service.ConmpanyInspectService;
import com.goboosoft.modules.security.user.SecurityUser;
import com.goboosoft.modules.sys.dto.SysDeptDTO;
import com.goboosoft.modules.sys.dto.SysUserDTO;
import com.goboosoft.modules.sys.service.SysDeptService;
import com.goboosoft.modules.sys.service.SysRegionService;
import com.goboosoft.modules.sys.service.SysUserService;
import com.goboosoft.modules.syscompany.dto.SysCompanyDTO;
import com.goboosoft.modules.syscompany.excel.SysCompanyExcel;
import com.goboosoft.modules.syscompany.service.SysCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@RestController
@RequestMapping("syscompany/syscompany")
@Api(tags = "基础 - 公司信息表")
public class SysCompanyController {
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Autowired
    private CompanyManageLogService companyManageLogService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysRegionService sysRegionService;


    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "公司名称", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "审核状态", paramType = "query", required = false, dataType = "String"),
    })
    public Result<PageData<SysCompanyDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        params.put("deptId", SecurityUser.getDeptId());
        // 查询当前用户是否部门
        SysUserDTO sysUserDTO = sysUserService.get(SecurityUser.getUserId());
        // 默认查询普通用户
        if (sysUserDTO.getIsDeptHead() == 0) {// 管理用户
            params.put("isDeptHead", 0);
        }
        PageData<SysCompanyDTO> page = sysCompanyService.pageList(params);
        return new Result<PageData<SysCompanyDTO>>().ok(page);
    }

    @GetMapping("pagePass")
    @ApiOperation("通过分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", required = false, dataType = "String"),
    })
    public Result<PageData<SysCompanyDTO>> pagePass(@ApiIgnore @RequestParam Map<String, Object> params) {
        params.put("deptId", SecurityUser.getDeptId());
        params.put("status", "1");

        // 查询当前用户是否部门
        SysUserDTO sysUserDTO = sysUserService.get(SecurityUser.getUserId());
        // 默认查询普通用户
        if (sysUserDTO.getIsDeptHead() == 0) {// 管理用户
            params.put("isDeptHead", 0);
        }

        PageData<SysCompanyDTO> page = sysCompanyService.pageList(params);
        return new Result<PageData<SysCompanyDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<SysCompanyDTO> get(@PathVariable("id") Long id) {
        SysCompanyDTO data = sysCompanyService.get(id);
        // 查询省市区
        if(data.getAdrProvince() != null){
            data.setAdrProvince(sysRegionService.get(Long.valueOf(data.getAdrProvince())).getName());
        }
        if(data.getAdrCity() != null){
            data.setAdrCity(sysRegionService.get(Long.valueOf(data.getAdrCity())).getName());
        }
        if(data.getAdrArea() != null){
            data.setAdrArea(sysRegionService.get(Long.valueOf(data.getAdrArea())).getName());
        }
        // 添加日志记录
        List<CompanyManageLogOneDTO> companyManageLogOneDTOList = new ArrayList<>();
        List<CompanyManageLogDTO> CompanyManageLogDTOList = companyManageLogService.findByComId(id);
        for (CompanyManageLogDTO companyManageLogDTO : CompanyManageLogDTOList) {
            CompanyManageLogOneDTO companyManageLogOneDTO = new CompanyManageLogOneDTO();
            companyManageLogOneDTO.setId(companyManageLogDTO.getId());
            companyManageLogOneDTO.setCause(companyManageLogDTO.getCause());
            companyManageLogOneDTO.setCreateDate(companyManageLogDTO.getCreateDate());
            if(companyManageLogDTO.getResult()!=null){
                companyManageLogOneDTO.setResult(companyManageLogDTO.getResult());
            }
            if(companyManageLogDTO.getAuditUser()!=null){
                companyManageLogOneDTO.setAuditUser(sysUserService.get(companyManageLogDTO.getAuditUser()).getRealName());
            }
            if(companyManageLogDTO.getType()!=null){
                companyManageLogOneDTO.setType(companyManageLogDTO.getType());
            }
            companyManageLogOneDTO.setCreator(sysUserService.get(Long.parseLong(companyManageLogDTO.getCreator())).getRealName());
            companyManageLogOneDTOList.add(companyManageLogOneDTO);

        }
        SysDeptDTO sysDeptDTO = sysDeptService.get(data.getXgdw());
        data.setDeptName(sysDeptDTO.getName());
        if(data.getUserId() != null) {
            Long userId = Long.valueOf(data.getUserId());
            data.setPrincipalName(sysUserService.get(userId).getRealName());
        }
        data.setCompanyManageLogOneDTOList(companyManageLogOneDTOList);
        // 查询分类
        List<ConmpanyInspectDTO> list = conmpanyInspectService.findConmpanyInspectBycomId(id);
        SysCompanyDTO inspectionNum = sysCompanyService.findInspectionNum(id);
        data.setTqNum(inspectionNum.getTqNum());
        data.setPkqfNum(inspectionNum.getPkqfNum());
        data.setDmNum(inspectionNum.getDmNum());
        data.setWfNum(inspectionNum.getWfNum());
        data.setConmpanyInspectDTO(list);
        return new Result<SysCompanyDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody SysCompanyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysCompanyService.save(dto);

        return new Result();
    }

    @PutMapping("audit")
    @ApiOperation("审核")
    @LogOperation("审核")
    public Result audit(@RequestBody SysCompanyDTO dto) {
        //效验数据

        sysCompanyService.audit(dto);

        return new Result();
    }

    @PutMapping("auditHi")
    @ApiOperation("审核历史记录")
    @LogOperation("审核历史记录")
    public Result auditHi(@RequestBody SysCompanyDTO dto) {
        //效验数据
        List<CompanyManageLogDTO> list = companyManageLogService.findByComId(dto.getId());
        return new Result().ok(list);
    }


    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody SysCompanyDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysCompanyService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysCompanyService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysCompanyDTO> list = sysCompanyService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, SysCompanyExcel.class);
    }

}