package com.goboosoft.company.companymanagelog.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Data
public class CompanyManageLogExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "审核结果")
    private String result;
    @Excel(name = "原因")
    private String cause;
    @Excel(name = "审核人")
    private Long auditUser;
    @Excel(name = "")
    private String creator;
    @Excel(name = "")
    private Date createDate;

}