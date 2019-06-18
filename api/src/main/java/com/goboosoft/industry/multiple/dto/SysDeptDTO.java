package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 基础 - 部门基本信息
 *
 * @author jinxin
 * created time 2019/3/5
 */
@Data
@ApiModel(value = "基础 - 部门基本信息")
public class SysDeptDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "上级ID")
	private Long pid;

	@ApiModelProperty(value = "所有上级ID，用逗号分开")
	private String pids;

	@ApiModelProperty(value = "部门名称")
	private String name;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "公司主键")
	private Long companyId;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新者")
	private Long updater;

	@ApiModelProperty(value = "更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "检查项ids")
	private String listIds;

}