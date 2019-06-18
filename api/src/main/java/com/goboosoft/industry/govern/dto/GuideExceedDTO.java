package com.goboosoft.industry.govern.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel(value = "指导-治理超期信息")
public class GuideExceedDTO {
    @ApiModelProperty(value = "治理计划id")
    private Long id;

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "超期项名称")
    private String name;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "单号")
    private String code;

    @ApiModelProperty(value = "治理人")
    private String person;

    @ApiModelProperty(value = "最晚治理时间")
    private Date endDate;

    @ApiModelProperty(value = "页码")
    private String rownum;
}
