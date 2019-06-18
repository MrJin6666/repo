package com.goboosoft.company.busininspect.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 业务 - 整改明细信息表
 *
 * @author DMXUAN ${email}
 * @since 1.0.0 2019-03-19
 */
@Data
public class BusinInspectionCorrectionMxExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "所属整改信息主键")
    private Long inspectionCorrectionId;
    @Excel(name = "所属检查项主键")
    private Long inspectionTaskItemId;
    @Excel(name = "所属检查项名称")
    private String inspectionTaskItemName;
    @Excel(name = "问题描述")
    private String problemDescription;
    @Excel(name = "问题图片")
    private String photos;
    @Excel(name = "检查内容代码")
    private Long inspectionListId;
    @Excel(name = "检查内容名称")
    private String inspectionListName;
    @Excel(name = "整改状态 0待整改 1已完成")
    private String correctionStatus;
    @Excel(name = "整改人id")
    private Long correctionUserId;
    @Excel(name = "整改人名称")
    private String correctionName;
    @Excel(name = "整改完成时间")
    private Date correctionDate;
    @Excel(name = "整改描述")
    private String finishRemark;
    @Excel(name = "整改图片")
    private String finishPhotos;
    @Excel(name = "整改人签名")
    private String underwrite;
    @Excel(name = "复查描述")
    private String checkRemark;
    @Excel(name = "复查图片")
    private String checkPhotos;
    @Excel(name = "复查人签名")
    private String checkUnderwrite;
    @Excel(name = "复查人id")
    private Long checkUserId;
    @Excel(name = "复查人名")
    private String checkUserName;

}