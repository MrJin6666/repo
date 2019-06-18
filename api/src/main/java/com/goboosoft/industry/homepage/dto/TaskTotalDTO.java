package com.goboosoft.industry.homepage.dto;

import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "首页任务数量统计")
public class TaskTotalDTO {
    @ApiModelProperty(value = "待处理数量")
    private Integer beProcessedNum;
    @ApiModelProperty(value = "超期数量")
    private Integer beyondTimeNum;
    @ApiModelProperty(value = "已完成数量")
    private Integer completedNum;
}
