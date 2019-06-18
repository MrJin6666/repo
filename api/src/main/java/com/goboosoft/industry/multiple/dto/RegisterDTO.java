/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "注册表单")
public class RegisterDTO {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String captcha;

    @ApiModelProperty(value = "用户类型 0 企业 1 行业")
    private Integer isCompany;

    @ApiModelProperty(value = "行业部门id")
    private Long deptId;

    @ApiModelProperty(value = "权限")
    private List<Long> permissions;

    @ApiModelProperty(value = "用户名")
    private String realName;

}
