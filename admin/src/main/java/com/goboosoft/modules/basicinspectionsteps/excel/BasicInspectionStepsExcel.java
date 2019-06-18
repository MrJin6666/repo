package com.goboosoft.modules.basicinspectionsteps.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Data
public class BasicInspectionStepsExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "治理步骤名称")
    private String name;
    @Excel(name = "治理顺序")
    private Long sort;
    @Excel(name = "")
    private Date createDate;
    @Excel(name = "")
    private Long creator;

}