package com.goboosoft.modules.basicinspectionlist.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
public class BasicInspectionListExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "检查项/检查内容名称")
    private String name;
    @Excel(name = "级别 0检查项 1检查内容")
    private String level;
    @Excel(name = "上级主键")
    private Long pid;
    @Excel(name = "总分")
    private Integer totalScore;

}