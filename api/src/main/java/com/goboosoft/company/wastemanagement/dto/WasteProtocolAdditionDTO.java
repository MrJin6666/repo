package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WasteProtocolAdditionDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "协议名称")
    private String protocolName;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "协议内容")
    private String content;
}