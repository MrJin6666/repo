package com.goboosoft.industry.statistics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统计-排名
 *
 * @author jinxin
 */
@Data
@ApiModel(value = "统计—重点监管")
public class RankingDTO {

    @ApiModelProperty(value = "区域")
    private String adrArea;

    @ApiModelProperty(value = "得分")
    private Integer score;

    @ApiModelProperty(value = "页码")
    private Integer rownum;
}
