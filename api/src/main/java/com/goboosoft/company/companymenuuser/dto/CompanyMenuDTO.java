package com.goboosoft.company.companymenuuser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@ApiModel(value = "企业菜单表")
public class CompanyMenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "菜单类型")
	private String type;

	@ApiModelProperty(value = "菜单名称")
	private String module;

	@ApiModelProperty(value = "")
	private String creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "名称")
	private String name;


}