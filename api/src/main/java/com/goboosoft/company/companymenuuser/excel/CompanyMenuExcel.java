package com.goboosoft.company.companymenuuser.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
public class CompanyMenuExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "菜单类型")
    private String type;
    @Excel(name = "菜单名称")
    private String module;
    @Excel(name = "")
    private String creator;
    @Excel(name = "")
    private Date createDate;

}