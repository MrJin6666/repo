package com.goboosoft.company.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "业务 - 整改反馈列表")
public class InspectionCorrectionListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "明细主键")
    private Long id;

    @ApiModelProperty(value = "单号")
    private String tickets;

    @ApiModelProperty(value = "所属检查任务主键")
    private Long inspectionTaskId;

    @ApiModelProperty(value = "状态 0新建  1已改正 2待审核 3审核通过 4审核失败")
    private Integer correctionStatus ;

    @ApiModelProperty(value = "整改状态")
    private String inspectionStatus;

    @ApiModelProperty(value = "所属检查项名称")
    private String name;

    @ApiModelProperty(value = "检查人姓名，多个使用,分割")
    private String inspectionUserName;

    @ApiModelProperty(value = "检查时间")
    private Date inspectionDate;

    @ApiModelProperty(value = "最晚整改时间")
    private Date lastCorrectionDate;

    private String rownum;
}
