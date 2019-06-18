package com.goboosoft.industry.govern.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "指导-牌详情信息")
public class GuideCardItemDTO {
    @ApiModelProperty(value = "任务详情id")
    private Long id;
    @ApiModelProperty(value = "公司id")
    private Date finishDate;
    @ApiModelProperty(value = "审核状态")
    private Integer checkStatus;
    @ApiModelProperty(value = "检查项id")
    private Long inspectionListId;
    @ApiModelProperty(value = "检查项名称")
    private String inspectionListName;
    @ApiModelProperty(value = "页码")
    private String rownum;
}
