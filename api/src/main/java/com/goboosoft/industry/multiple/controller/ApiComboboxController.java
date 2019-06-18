package com.goboosoft.industry.multiple.controller;

import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.industry.multiple.result.ComboboxResult;
import com.goboosoft.industry.multiple.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Combobox控件数据管理
 * created by yangkun
 * created time 2019/2/11
 * class describe
 */
@RestController
@RequestMapping("/combobox")
@Api(tags="Combobox数据管理")
public class ApiComboboxController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("getCompanyByCompanyType")
    @ApiOperation(value = "企业名称Combobox", response = ComboboxResult.class)
    public Result getCompanyByCompanyType(
            @ApiParam(value = "企业类型", name = "companyType") @RequestParam String companyType){
        List<SysCompanyEntity> companyEntityList = companyService.getCompanyListByCompanyType(companyType);
        List<ComboboxResult> comboboxResultList = null;
        if(companyEntityList != null && companyEntityList.size() > 0){
            comboboxResultList = new ArrayList<>();
            for(SysCompanyEntity companyEntity : companyEntityList){
                ComboboxResult cr = new ComboboxResult();
                cr.setId(companyEntity.getId());
                cr.setValue(companyEntity.getName());
                comboboxResultList.add(cr);
            }
        }
        return new Result().ok(comboboxResultList);
    }
}
