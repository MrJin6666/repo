package com.goboosoft.industry.statistics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 企业基本信息
 */
@Data
@ApiModel(value = "督导人次")
public class SupervisePeopleDTO {
    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "季")
    private String quarter;

    @ApiModelProperty(value = "月")
    private String month;

    @ApiModelProperty(value = "周")
    private String week;

    @ApiModelProperty(value = "人次")
    private Integer peopleNum ;

}
