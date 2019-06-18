package com.goboosoft.company.busininspect.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Data
public class BusinInspectionCorrectionExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "整改企业主键")
    private Long companyId;
    @Excel(name = "整改企业名称")
    private String companyName;
    @Excel(name = "所属检查任务主键")
    private Long inspectionTaskId;
    @Excel(name = "状态 0新建  1已改正 2待审核 3审核通过 4审核失败")
    private Integer status;
    @Excel(name = "检查人姓名，多个使用,分割")
    private String inspectionUserName;
    @Excel(name = "检查时间")
    private Date inspectionDate;
    @Excel(name = "改正时间")
    private Date correctionDate;
    @Excel(name = "最晚整改时间")
    private Date lastCorrectionDate;

}