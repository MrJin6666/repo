package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OutboundWasteAdditionGroupDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "OutboundWasteAdditionDTO")
    private List<OutboundWasteAdditionDTO> OutboundData;

    @ApiModelProperty(value = "接收单位")
    private String receiverUnit;

    @ApiModelProperty(value = "所属管理部门")
    private String belongDept;

    @ApiModelProperty(value = "出库时间")
    private Date outboundDate;

    @ApiModelProperty(value = "危废去向")
    private String wasteDirection;

    @ApiModelProperty(value = "关联转移联单")
    private String transfer;

    @ApiModelProperty(value = "出库人签字")
    private String outSignature;

    @ApiModelProperty(value = "接收单位签字")
    private String unitSignature;
}
