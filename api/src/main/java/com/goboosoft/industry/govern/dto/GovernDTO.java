package com.goboosoft.industry.govern.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "治理信息")
public class GovernDTO {
    @ApiModelProperty(value = "公司id")
    private String companyId;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "治理时间")
    private Date seatDate;
    @ApiModelProperty(value = "页码")
    private String rownum;

}
