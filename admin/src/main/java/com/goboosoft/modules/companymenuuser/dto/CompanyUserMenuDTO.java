package com.goboosoft.modules.companymenuuser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@ApiModel(value = "用户企业菜单表")
public class CompanyUserMenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "用户id")
	private Long userId;

	@ApiModelProperty(value = "企业菜单模块")
	private String companyMenuModule;

	@ApiModelProperty(value = "")
	private String creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	private CompanyMenuDTO companyMenuDTO;


}