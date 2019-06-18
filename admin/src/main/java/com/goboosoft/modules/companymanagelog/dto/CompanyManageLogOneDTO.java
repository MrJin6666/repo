package com.goboosoft.modules.companymanagelog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Data
@ApiModel(value = "企业管理日志")
public class CompanyManageLogOneDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日志id")
	private Long id;

	@ApiModelProperty(value = "审核结果")
	private String result;

	@ApiModelProperty(value = "原因")
	private String cause;

	@ApiModelProperty(value = "审核人")
	private String auditUser;

	@ApiModelProperty(value = "创建者")
	private String creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "类型")
	private String type;


}