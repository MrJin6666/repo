package com.goboosoft.company.companymanagelog.controller;



import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.entity.CompanyManageLogEntity;
import com.goboosoft.company.companymanagelog.excel.CompanyManageLogExcel;
import com.goboosoft.company.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.security.user.UserDetail;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@RestController
@RequestMapping("companymanagelog")
@Api(tags="企业管理日志")
public class CompanyManageLogsController {
    @Autowired
    private CompanyManageLogService companyManageLogService;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    //@RequiresPermissions("demo:companymanagelog:page")
    public Result<PageData<CompanyManageLogDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CompanyManageLogDTO> page = companyManageLogService.page(params);

        return new Result<PageData<CompanyManageLogDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:companymanagelog:info")
    public Result<CompanyManageLogDTO> get(@PathVariable("id") Long id){
        CompanyManageLogDTO data = companyManageLogService.get(id);

        return new Result<CompanyManageLogDTO>().ok(data);
    }


    @PostMapping
    @ApiOperation("保存")
    //@LogOperation("保存")
    @RequiresPermissions("demo:companymanagelog:save")
    public Result save(@RequestBody CompanyManageLogDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        companyManageLogService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    //@LogOperation("修改")
    @RequiresPermissions("demo:companymanagelog:update")
    public Result update(@RequestBody CompanyManageLogDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        companyManageLogService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    //@LogOperation("删除")
    @RequiresPermissions("demo:companymanagelog:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        companyManageLogService.delete(ids);

        return new Result();
    }

    @PostMapping("getLists")
    @ApiOperation("获取企业申请日志")
    public Result<List<CompanyManageLogDTO>> getLists(){
        Long companyId = SecurityUser.getUser().getCompanyId();
        Long userId = SecurityUser.getUser().getId();
//        if(companyId==null){
//            ConmpanyInspectDTO conmpanyInspectDTO = conmpanyInspectService.getCompanyIdByUser(SecurityUser.getUserId());
//        }
        List<CompanyManageLogEntity> lists = companyManageLogService.getLists(companyId);
        List<CompanyManageLogDTO> manageLogDTOS = ConvertUtils.sourceToTarget(lists, CompanyManageLogDTO.class);

        for (int i = 0; i < manageLogDTOS.size(); i++) {
//            Long auditUserId = manageLogDTOS.get(i).getAuditUser();
            Long logCreatorId = lists.get(i).getCreator();
            if (logCreatorId != null){//数据库有空数据  所以加了“null”判断（下同）
                SysUserDTO sysUserDTO = sysUserService.get(logCreatorId);
                if (sysUserDTO != null){
                    manageLogDTOS.get(i).setAuditUserName(sysUserDTO.getRealName());
                }
                if (userId.longValue() != logCreatorId.longValue()){
                    manageLogDTOS.get(i).setIsSubmit(1);
                }else {
                    manageLogDTOS.get(i).setIsSubmit(0);
                }
            }else {
                manageLogDTOS.get(i).setIsSubmit(1);
            }

        }

        return new Result<List<CompanyManageLogDTO>>().ok(manageLogDTOS);
    }

}