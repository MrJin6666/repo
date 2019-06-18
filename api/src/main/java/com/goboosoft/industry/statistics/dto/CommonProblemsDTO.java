package com.goboosoft.industry.statistics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 企业常见问题
 */
@Data
@ApiModel(value = "企业常见问题")
public class CommonProblemsDTO {

    @ApiModelProperty(value = "问题id")
    private String inspectionListId;

    @ApiModelProperty(value = "问题名称")
    private String inspectionListName;

    @ApiModelProperty(value = "出现次数")
    private String number;

    @ApiModelProperty(value = "页数")
    private Integer rownum;
}
