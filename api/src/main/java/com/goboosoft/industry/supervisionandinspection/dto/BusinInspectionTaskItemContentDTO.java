package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 督导检查任务项内容信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "业务 - 督导检查任务项内容信息表")
public class BusinInspectionTaskItemContentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "督导检查任务项主键")
	private Long inspectionTaskItemId;

	@ApiModelProperty(value = "检查内容代码")
	private Long inspectionListId;

	@ApiModelProperty(value = "检查内容名称")
	private String inspectionListName;

	@ApiModelProperty(value = "本项得分")
	private Integer score;

	@ApiModelProperty(value = "本项满分")
	private Integer totalScore;

	@ApiModelProperty(value = "合格状态 0不合格 1合格")
	private String passStatus;

	@ApiModelProperty(value = "评价")
	private String remark;

	@ApiModelProperty(value = "图片")
	private String photos;

}