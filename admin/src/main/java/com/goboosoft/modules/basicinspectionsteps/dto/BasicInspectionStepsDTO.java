package com.goboosoft.modules.basicinspectionsteps.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Data
@ApiModel(value = "基础 - 检查项步骤信息表")
public class BasicInspectionStepsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "治理步骤名称")
	private String name;

	@ApiModelProperty(value = "治理顺序")
	private Long sort;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "")
	private Long creator;

	@ApiModelProperty(value = "检查项id")
	private Long inspectionListId;


}