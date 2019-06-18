package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 基础 - 部门基本信息
 *
 * @author jinxin
 * created time 2019/3/5
 */
@Data
@ApiModel(value = "基础 - 部门基本信息")
public class SysSameLevelDeptDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "部门id")
	private Long id;

	@ApiModelProperty(value = "上级ID")
	private Long pid;

	@ApiModelProperty(value = "部门名称")
	private String name;



}