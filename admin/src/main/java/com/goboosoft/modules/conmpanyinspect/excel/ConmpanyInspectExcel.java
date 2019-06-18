package com.goboosoft.modules.conmpanyinspect.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
public class ConmpanyInspectExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "检查项id")
    private Long listId;
    @Excel(name = "用户id")
    private Long userId;
    @Excel(name = "图片")
    private String picture;
    @Excel(name = "")
    private Long creator;
    @Excel(name = "")
    private Date createDate;

}