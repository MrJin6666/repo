package com.goboosoft.modules.companymenuuser.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
public class CompanyUserMenuExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "用户id")
    private Long userId;
    @Excel(name = "企业菜单id")
    private Long companyMenuId;
    @Excel(name = "")
    private String creator;
    @Excel(name = "")
    private Date createDate;

}