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
public class CompanyManageLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "审核结果")
	private String result;

	@ApiModelProperty(value = "原因")
	private String cause;

	@ApiModelProperty(value = "审核人")
	private Long auditUser;

	@ApiModelProperty(value = "")
	private String creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	private long companyId;
	@ApiModelProperty(value = "类型")
	private String type;


}