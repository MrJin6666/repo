package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "企业危废管理协议")
public class WasteProtocolDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "协议id")
	private Long id;

	@ApiModelProperty(value = "协议名称")
	private String protocolName;

	@ApiModelProperty(value = "开始时间")
	private String startDate;

	@ApiModelProperty(value = "结束时间")
	private String endDate;

	@ApiModelProperty(value = "协议内容")
	private String content;

	@ApiModelProperty(value = "企业id")
	private Long companyId;
}