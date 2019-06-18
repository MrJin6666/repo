package com.goboosoft.modules.companymanagesystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
 */
@Data
@ApiModel(value = "企业管理制度")
public class CompanyManageSystemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "通告id")
	private Long id;

	@ApiModelProperty(value = "通告名称")
	private String noticeName;

	@ApiModelProperty(value = "创造者名称")
	private String creatorName;

	@ApiModelProperty(value = "通告内容")
	private String noticeContent;

	@ApiModelProperty(value = "附件（上传图片）")
	private String attachment;

	@ApiModelProperty(value = "创造者ID")
	private Long creator;

	@ApiModelProperty(value = "上传时间")
	private Date createDate;

	@ApiModelProperty(value = "是否是企业（0：企业，1：部门）")
	private Integer isCompany;

	@ApiModelProperty(value = "0：正常 1：不可用（删除数据时标记为“1”）")
	private Integer state;

	@ApiModelProperty(value = "图标")
	private String iconUri;

	@ApiModelProperty(value = "部门id")
	private Long deptId;

	@ApiModelProperty(value = "类型 0 制度 1 公告")
	private int type;


}