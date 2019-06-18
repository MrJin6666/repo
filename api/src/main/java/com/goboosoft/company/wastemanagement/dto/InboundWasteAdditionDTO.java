package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InboundWasteAdditionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废类型")
    private String wasteType;

    @ApiModelProperty(value = "危废名称")
    private String wasteName;

    @ApiModelProperty(value = "计数")
    private Integer count;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "危废来源")
    private String wasteSource;

    @ApiModelProperty(value = "保存位置")
    private String saveLocation;

    @ApiModelProperty(value = "入库人签字")
    private String inSignature;
}
