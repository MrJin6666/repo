package com.goboosoft.system.sys.controller;

import com.goboosoft.common.annotation.LogOperation;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.utils.Result;
import com.goboosoft.common.validator.AssertUtils;
import com.goboosoft.common.validator.ValidatorUtils;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import com.goboosoft.system.sys.dto.SysRegionDTO;
import com.goboosoft.system.sys.service.SysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@RestController
@RequestMapping("demo/sysregion")
@Api(tags="省市区查询")
public class SysRegionController {
    @Autowired
    private SysRegionService sysRegionService;

    @GetMapping("id")
    @ApiOperation("信息")
    public Result<SysRegionDTO> get(@RequestParam("adrProvince") String adrProvince,@RequestParam("adrCity") String adrCity ){
        System.out.println(adrProvince);
        System.out.println(adrCity);
        List<SysRegionDTO> data = sysRegionService.getRegion(adrProvince,adrCity);
        return new Result().ok(data);
    }



}