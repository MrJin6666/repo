package com.goboosoft.modules.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 业务 - 督导检查任务项信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "业务 - 督导检查任务项信息表")
public class BusinInspectionTaskItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "检查项名称")
	private Long inspectionTaskId;

	@ApiModelProperty(value = "检查项代码")
	private Long inspectionListId;

	@ApiModelProperty(value = "检查项名称")
	private String inspectionListName;

	@ApiModelProperty(value = "本项得分")
	private Integer score;

	@ApiModelProperty(value = "本项满分")
	private Integer totalScore;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "状态 0新建 1完成")
	private Integer status;

	@ApiModelProperty(value = "合格状态 0不合格 1合格")
	private Integer passStatus;

	@ApiModelProperty(value = "来源 : 1 任务 2 修改")
	private String source;


}