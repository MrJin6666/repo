package com.goboosoft.company.companyinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Data
@ApiModel(value = "企业检查项")
public class ConmpanyInspectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "检查项id")
	private Long listId;

	@ApiModelProperty(value = "用户id")
	private Long userId;

	@ApiModelProperty(value = "图片")
	private String picture;

	@ApiModelProperty(value = "")
	private Long creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "检查项名称")
	private String name;

	@ApiModelProperty(value = "检查项总数")
	private Long count;

	@ApiModelProperty(value = "公司id")
	private Long companyId;

	@ApiModelProperty(value = "检查项实例")
	private String roomId;
}