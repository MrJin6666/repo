/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goboosoft.common.utils.TreeNode;
import com.goboosoft.common.validator.group.AddGroup;
import com.goboosoft.common.validator.group.DefaultGroup;
import com.goboosoft.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@ApiModel(value = "本部门及本部门从属部门所有人员")
public class SysDeptAllPersionDTO implements Serializable {

	@ApiModelProperty(value = "部门id")
	private Long deptId;

	@ApiModelProperty(value = "用户Id")
	private Long userId;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "用户手机号")
	private String mobile;

}