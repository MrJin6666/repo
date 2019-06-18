package com.goboosoft.company.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "业务 - 整改详情")
public class CompanyDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "合格状态 0未改正 1已改正")
    private String correctionStatus;

    @ApiModelProperty(value = "检查人姓名，多个使用,分割")
    private String inspectionUserName;


    @ApiModelProperty(value = "最晚整改时间")
    private Date lastCorrectionDate;

}
