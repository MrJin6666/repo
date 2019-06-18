package com.goboosoft.company.govern.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
public class GovernProcessPlanExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "治理计划id")
    private Long governPlanId;
    @Excel(name = "图片")
    private String picture;
    @Excel(name = "过程 1.待处理 2.处理中 3.已完成 4.超期")
    private Integer process;
    @Excel(name = "治理描述")
    private String describ;
    @Excel(name = "管理员签字")
    private String sign;
    @Excel(name = "抽查时间")
    private Date selectDate;
    @Excel(name = "抽查时限")
    private Date selectLimitDate;
    @Excel(name = "创建人")
    private String creator;
    @Excel(name = "创建时间")
    private Date createDate;

}