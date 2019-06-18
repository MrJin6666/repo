package com.goboosoft.modules.supervisionandinspection.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 业务 - 检查任务信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
public class BusinInspectionTaskExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "企业主键")
    private Long companyId;
    @Excel(name = "企业名称")
    private String companyName;
    @Excel(name = "检查人主键")
    private Long inspectUserId;
    @Excel(name = "检查人名称")
    private String inspectUserName;
    @Excel(name = "随行人员主键")
    private String accompanyUserIds;
    @Excel(name = "随行人员名称")
    private String accompanyUserNames;
    @Excel(name = "督导日期")
    private Date inspectDate;
    @Excel(name = "整改人主键")
    private Long correctionUserId;
    @Excel(name = "整改人名称")
    private String correctionUserName;
    @Excel(name = "合格状态 0不合格 1合格")
    private String passStatus;
    @Excel(name = "")
    private Long creator;
    @Excel(name = "")
    private Date createDate;
    @Excel(name = "")
    private Long updater;
    @Excel(name = "")
    private Date updateDate;
    @Excel(name = "单号")
    private String tickets;

}