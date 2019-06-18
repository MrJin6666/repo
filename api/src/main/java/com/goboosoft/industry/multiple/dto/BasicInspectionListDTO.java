package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
@ApiModel(value = "基础 - 检查项/检查内容信息表")
public class BasicInspectionListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "检查项/检查内容名称")
	private String name;

	@ApiModelProperty(value = "级别 0检查项 1检查内容")
	private String level;

	@ApiModelProperty(value = "上级主键")
	private Long pid;

	@ApiModelProperty(value = "总分")
	private Integer totalScore;

	private Integer count;

	@ApiModelProperty(value = "周期")
	private long cycle;

	private Long companyInspectId;

}