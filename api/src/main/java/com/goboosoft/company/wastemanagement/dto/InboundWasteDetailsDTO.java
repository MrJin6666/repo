package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InboundWasteDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废id")
    private Long id;

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

    @ApiModelProperty(value = "更新人名称")
    private String inName;

    @ApiModelProperty(value = "入库时间")
    private Date createDate;

    @ApiModelProperty(value = "入库人签字")
    private String inSignature;

    @ApiModelProperty(value = "是否确认（0：未确认 1：确认）")
    private Integer isVerify;

    @ApiModelProperty(value = "管理员名称")
    private String admin;

    @ApiModelProperty(value = "入库管理员签字")
    private String adminSignature;

    @ApiModelProperty(value = "入库管理员确认时间")
    private Date verifyDate;

}
