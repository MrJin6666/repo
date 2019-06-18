package com.goboosoft.industry.company.controller;

import com.goboosoft.common.utils.Result;
import com.goboosoft.industry.company.dto.QueryProblemCompanyNumberDTO;
import com.goboosoft.industry.company.service.QueryProblemCompanyNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 查询问题企业个数（带权限）
 *
 * @author jinxin
 */
@RestController
@RequestMapping("/query")
@Api(tags = "查询问题企业个数（带权限）")
public class ApiQueryProblemCompanyNumberController {
    @Autowired
    private QueryProblemCompanyNumberService queryProblemCompanyNumberService;

    @GetMapping("queryProblemCompanyNumber")
    @ApiOperation(value = "查询问题企业个数")
    public Result<QueryProblemCompanyNumberDTO> queryProblemCompanyNumber() {
        QueryProblemCompanyNumberDTO number = queryProblemCompanyNumberService.queryNumber();
        return new Result<QueryProblemCompanyNumberDTO>().ok(number);
    }


}
