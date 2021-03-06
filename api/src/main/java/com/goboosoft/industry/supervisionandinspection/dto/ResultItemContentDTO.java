package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 业务 - 督导检查任务项内容信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "督导检查任务项内容结果")
public class ResultItemContentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "检查内容名称")
	private String inspectionListName;

	@ApiModelProperty(value = "本项满分")
	private Integer totalScore;

	@ApiModelProperty(value = "本项得分")
	private Integer score;

}