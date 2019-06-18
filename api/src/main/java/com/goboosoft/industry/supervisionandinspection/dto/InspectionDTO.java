package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "获取检查项")
public class InspectionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "检查项代码")
	private Long id;

	@ApiModelProperty(value = "检查项名称")
	private String name;

	@ApiModelProperty(value = "总分数")
	private Integer totalScore;

}