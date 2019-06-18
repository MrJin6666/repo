package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OutboundWasteDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废id")
    private Long id;

    @ApiModelProperty(value = "危废类型")
    private String wasteType;

    @ApiModelProperty(value = "危废名称")
    private String wasteName;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "危废去向")
    private String wasteDirection;

    @ApiModelProperty(value = "出库人名称")
    private String outName;

    @ApiModelProperty(value = "出库时间")
    private Date outboundDate;

    @ApiModelProperty(value = "出库人签字")
    private String outSignature;

    @ApiModelProperty(value = "接收单位签字")
    private String unitSignature;

    @ApiModelProperty(value = "接收单位")
    private String receiverUnit;

    @ApiModelProperty(value = "转移联单")
    private String transfer;
}
