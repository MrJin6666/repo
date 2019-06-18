package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@ApiModel(value = "")
public class WasteManageDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "危废id")
	private Long id;

	@ApiModelProperty(value = "危废类型")
	private String wasteType;

	@ApiModelProperty(value = "危废名称")
	private String wasteName;

	@ApiModelProperty(value = "计数")
	private Integer count;

	@ApiModelProperty(value = "计量单位")
	private String unit;

	@ApiModelProperty(value = "危废来源")
	private String wasteSource;

	@ApiModelProperty(value = "危废去向")
	private String wasteDirection;

	@ApiModelProperty(value = "保存位置")
	private String saveLocation;

	@ApiModelProperty(value = "更新人名称")
	private String inName;

	@ApiModelProperty(value = "出库人名称")
	private String outName;

	@ApiModelProperty(value = "出库单号")
	private String outboundNum;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "出库时间")
	private Date outboundDate;

	@ApiModelProperty(value = "入库人签字")
	private String inSignature;

	@ApiModelProperty(value = "出库人签字")
	private String outSignature;

	@ApiModelProperty(value = "接收单位签字")
	private String unitSignature;

	@ApiModelProperty(value = "接收单位")
	private String receiverUnit;

	@ApiModelProperty(value = "所属管理部门")
	private String belongDept;

	@ApiModelProperty(value = "转移联单")
	private String transfer;

	@ApiModelProperty(value = "企业id")
	private Long companyId;

	@ApiModelProperty(value = "是否确认（0：未确认 1：确认）")
	private Integer isVerify;

	@ApiModelProperty(value = "入库或出库（0：入库 1：出库）")
	private Integer inboundOrOutbound;

	@ApiModelProperty(value = "管理员名称")
	private String admin;

	@ApiModelProperty(value = "入库管理员签字")
	private String adminSignature;

	@ApiModelProperty(value = "入库管理员确认时间")
	private Date verifyDate;

	private List<Long> ids;

	private Integer balanceNum;

	private String outboundDateSting;

}