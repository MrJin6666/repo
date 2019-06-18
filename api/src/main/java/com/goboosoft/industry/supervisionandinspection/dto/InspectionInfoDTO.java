package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "获取督导检查项")
public class InspectionInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "检查项代码")
	private Long id;

	@ApiModelProperty(value = "检查项名称")
	private String inspectionListName;

	@ApiModelProperty(value = "本项得分")
	private Integer score;

	@ApiModelProperty(value = "本项满分")
	private Integer totalScore;

}