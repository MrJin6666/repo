package com.goboosoft.company.govern.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
public class GovernPlanExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "治理人")
    private String person;
    @Excel(name = "单号")
    private String code;
    @Excel(name = "治理时间")
    private Date seatDate;
    @Excel(name = "创建人")
    private String creator;
    @Excel(name = "创建时间(计划开始时间)")
    private Date createDate;
    @Excel(name = "计划结束时间")
    private Date endDate;
    @Excel(name = "项目名称")
    private String name;
    @Excel(name = "治理状态(0 待处理)")
    private String status;
    @Excel(name = "计划状态 1 抽查 2 治理")
    private String planStatus;
    @Excel(name = "治理项id")
    private Long inspectionListId;

}