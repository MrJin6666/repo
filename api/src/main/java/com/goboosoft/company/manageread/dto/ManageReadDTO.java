package com.goboosoft.company.manageread.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 公告记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-20
 */
@Data
@ApiModel(value = "公告记录")
public class ManageReadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "已读人id")
	private Long finshRead;

	@ApiModelProperty(value = "")
	private Long creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "公告id")
	private Long manageSystemId;

	@ApiModelProperty(value = "已读人姓名")
	private String finshReadName;

	@ApiModelProperty(value = "0 企业 1 行业")
	private Integer isCompany;

	private String headUrl;

	private Long companyId;

}