package com.goboosoft.modules.busininspect.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 业务 - 整改明细信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Data
public class BusinInspectionCorrectionMxExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "所属整改信息主键")
    private Long inspectionCorrectionId;
    @Excel(name = "所属检查项主键")
    private Long inspectionTaskItemId;
    @Excel(name = "")
    private String problemDescription;
    @Excel(name = "检查内容代码")
    private Long inspectionListId;
    @Excel(name = "检查内容名称")
    private String inspectionListName;
    @Excel(name = "整改状态 0待整改 1已完成")
    private String correctionStatus;
    @Excel(name = "整改完成时间")
    private Date correctionDate;

}