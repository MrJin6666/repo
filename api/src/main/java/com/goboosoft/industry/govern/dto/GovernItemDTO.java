package com.goboosoft.industry.govern.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "当日公司列表信息")
public class GovernItemDTO {

    @ApiModelProperty(value = "治理计划id")
    private Long id;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "治理项名称")
    private String governItemName;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "单号")
    private String code;

    @ApiModelProperty(value = "治理人")
    private String person;

    @ApiModelProperty(value = "完成时间")
    private Date seatDate;

    @ApiModelProperty(value = "最晚治理时间")
    private Date endDate;

    @ApiModelProperty(value = "页码")
    private String rownum;
}
