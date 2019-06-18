package com.goboosoft.company.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "业务 - 整改信息详情")
public class InspectionCorrectionDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "单号")
    private String tickets;

    @ApiModelProperty(value = "检查内容名称")
    private String inspectionListName;

    @ApiModelProperty(value = "检查结果0：不合格，1：合格")
    private String passStatus;

    @ApiModelProperty(value = "检查人姓名，多个使用,分割")
    private String inspectionUserName;

    @ApiModelProperty(value = "最晚整改时间")
    private Date lastCorrectionDate;

    @ApiModelProperty(value = "问题描述")
    private String problemDescription;

    @ApiModelProperty(value = "问题图片")
    private String photos;

    @ApiModelProperty(value = "检查人签名")
    private String underwrite;

}
