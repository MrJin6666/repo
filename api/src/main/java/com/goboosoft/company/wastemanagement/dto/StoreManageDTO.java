package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StoreManageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "危废id")
    private Long id;

    @ApiModelProperty(value = "危废名称")
    private String wasteName;

    @ApiModelProperty(value = "计数")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

//    @ApiModelProperty(value = "出库时间")
//    private Date outboundDate;

    @ApiModelProperty(value = "最后入库时间(入库管理员确认时间)")
    private Date verifyDate;
//
//    @ApiModelProperty(value = "最后入库时间")
//    private Date lastInBoundDate;
//
    @ApiModelProperty(value = "结算数量")
    private Integer balanceNum;

    @ApiModelProperty(value = "入库或出库（0：入库 1：出库）")
    private Integer inboundOrOutbound;
}
