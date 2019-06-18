package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OutboundWasteListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废id")
    private Long id;

    @ApiModelProperty(value = "出库单号")
    private String outboundNum;

    @ApiModelProperty(value = "更新人名称")
    private String outName;

    @ApiModelProperty(value = "出库时间")
    private Date outboundDate;

    @ApiModelProperty(value = "转移联单")
    private String transfer;

}
