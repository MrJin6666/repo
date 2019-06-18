package com.goboosoft.industry.homepage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "检查项详情统计")
public class InspectDetailsDTO {
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "检查项id")
    private Long inspectId;
    @ApiModelProperty(value = "检查项名称")
    private String inspectName;
    @ApiModelProperty(value = "检查项总数")
    private Integer inspectTotal;

}
