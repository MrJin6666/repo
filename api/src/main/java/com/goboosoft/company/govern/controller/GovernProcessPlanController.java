package com.goboosoft.company.govern.controller;


import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.ExcelUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.company.govern.dto.GovernPlanDTO;
import com.goboosoft.company.govern.dto.GovernProcessPlanDTO;
import com.goboosoft.company.govern.excel.GovernProcessPlanExcel;
import com.goboosoft.company.govern.service.GovernPlanService;
import com.goboosoft.company.govern.service.GovernProcessPlanService;
import com.goboosoft.system.security.user.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@RestController
@RequestMapping("governprocessplan")
@Api(tags = "治理计划过程")
public class GovernProcessPlanController {
    @Autowired
    private GovernProcessPlanService governProcessPlanService;
    @Autowired
    private GovernPlanService governPlanService;
    @Value("${filePath}")
    private String filePath;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<GovernProcessPlanDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<GovernProcessPlanDTO> page = governProcessPlanService.page(params);

        return new Result<PageData<GovernProcessPlanDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<GovernProcessPlanDTO> get(@PathVariable("id") Long id) {
        GovernProcessPlanDTO data = governProcessPlanService.get(id);

        return new Result<GovernProcessPlanDTO>().ok(data);
    }

    @PostMapping("saveProcess")
    @ApiOperation("治理过程")
    @LogOperation("治理过程")
    public Result save(@RequestBody GovernProcessPlanDTO dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Result result = new Result();
        try {
            if (dto.getId() == null) {
                //效验数据
                ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
                Long governPlanId = dto.getGovernPlanId();
                GovernPlanDTO governPlanDTO = governPlanService.get(governPlanId);
                Date endDate = governPlanDTO.getEndDate();
                if (dto.getProcess() == 3) {//处理状态：0 待处理 1 处理中 2 处理后 3 已完成 4 超期 5 延期 6治理前
                    governPlanDTO.setSeatDate(new Date());
                    governPlanDTO.setStatus(dto.getProcess().toString());
                    governPlanDTO.setPerson(SecurityUser.getUser().getRealName());
                    governPlanService.update(governPlanDTO);
                }else if ((dto.getProcess() == 5) ){//延期
                    Calendar ca = Calendar.getInstance();
                    ca.add(Calendar.DATE, 2);  //延长2天
                    endDate = ca.getTime();
                    governPlanDTO.setEndDate(endDate);
                    governPlanDTO.setStatus(dto.getProcess().toString());
                    governPlanService.update(governPlanDTO);
                }else if (dto.getProcess() != 5){
                    governPlanDTO.setStatus(dto.getProcess().toString());
                    governPlanService.update(governPlanDTO);
                }
                governProcessPlanService.save(dto);
                result.ok("保存成功！");
            } else {
                GovernProcessPlanDTO governProcessPlanDTO = governProcessPlanService.get(dto.getId());
                GovernPlanDTO governPlanDTO = governPlanService.get(governProcessPlanDTO.getGovernPlanId());
                governPlanDTO.setStatus(dto.getProcess().toString());
                governPlanDTO.setPerson(SecurityUser.getUser().getRealName());
                governPlanDTO.setSeatDate(new Date());
                dto.setGovernPlanId(governProcessPlanDTO.getGovernPlanId());
                dto.setSelectDate(governProcessPlanDTO.getSelectDate());
                dto.setSelectLimitDate(governProcessPlanDTO.getSelectLimitDate());
                governProcessPlanService.update(dto);
                governPlanService.update(governPlanDTO);
                result.ok("保存成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.error("保存失败！");
        }
        return result;
    }

    @GetMapping("delayPlan")
    @ApiOperation("延迟治理提交")
    @LogOperation("延迟治理提交")
    public Result delayPlan(@RequestBody GovernProcessPlanDTO governProcessPlanDTO) {
        Result result = new Result();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            GovernPlanDTO governPlanDTO = governPlanService.get(governProcessPlanDTO.getGovernPlanId());
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.DATE, 2);  //延长2天
            Date endDate = governPlanDTO.getEndDate();
            endDate = ca.getTime();
            governPlanDTO.setEndDate(endDate);
            governProcessPlanService.save(governProcessPlanDTO);
            result.ok("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.error("保存失败！");
        }
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:governprocessplan:update")
    public Result update(@RequestBody GovernProcessPlanDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        governProcessPlanService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:governprocessplan:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        governProcessPlanService.delete(ids);

        return new Result();
    }

}