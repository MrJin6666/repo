package com.goboosoft.industry.company.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 问题企业总数
 * @author jinxin
 */
@Data
@ApiModel(value = "问题企业总数信息")
public class QueryProblemCompanyNumberDTO {
    @ApiModelProperty(value = "红牌总个数")
    private Integer redCardCount;
    @ApiModelProperty(value = "黄牌总个数")
    private Integer yellowCardCount;
    @ApiModelProperty(value = "一般总个数")
    private Integer ordinaryCardCount;
}
