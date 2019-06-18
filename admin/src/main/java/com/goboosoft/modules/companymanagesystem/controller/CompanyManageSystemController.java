package com.goboosoft.modules.companymanagesystem.controller;

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
import com.goboosoft.modules.companymanagesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.modules.companymanagesystem.excel.CompanyManageSystemExcel;
import com.goboosoft.modules.companymanagesystem.service.CompanyManageSystemService;
import com.goboosoft.modules.security.user.SecurityUser;
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
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
 */
@RestController
@RequestMapping("companymanagesystem/companymanagesystem")
@Api(tags="企业管理制度")
public class CompanyManageSystemController {
    @Autowired
    private CompanyManageSystemService companyManageSystemService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "noticeName", value = "标题", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "type", value = "类型", paramType = "query", dataType="int")
    })
    public Result<PageData<CompanyManageSystemDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("deptId", SecurityUser.getDeptId());
        params.put("userId", SecurityUser.getUserId());// 可以查询自己发布的公告
        PageData<CompanyManageSystemDTO> page = companyManageSystemService.pageList(params);
        return new Result<PageData<CompanyManageSystemDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<CompanyManageSystemDTO> get(@PathVariable("id") Long id){
        CompanyManageSystemDTO data = companyManageSystemService.get(id);
        return new Result<CompanyManageSystemDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody CompanyManageSystemDTO dto){
        //效验数据
        //ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        // SecurityUser.getUser().setDeptId(dto.getDeptId());
        dto.setIsCompany(1);
        companyManageSystemService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody CompanyManageSystemDTO dto){
        //效验数据
        //ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        companyManageSystemService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
       // AssertUtils.isArrayEmpty(ids, "id");

        companyManageSystemService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CompanyManageSystemDTO> list = companyManageSystemService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CompanyManageSystemExcel.class);
    }

}