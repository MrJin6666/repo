package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 行业 - 企业抽查样本列表
 *
 * @author yuzhao
 * @since 1.0.0 2019-03-11
 */
@Data
@ApiModel(value = "行业 - 企业抽查样本列表")
public class SpotCheckSampleListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "样本项主键")
	private Long id;

	@ApiModelProperty(value = "订单号")
	private String code;

	@ApiModelProperty(value = "企业id")
	private Long companyId;

	@ApiModelProperty(value = "企业名称")
	private String companyName;

	@ApiModelProperty(value = "项目名称")
	private String name;

	@ApiModelProperty(value = "治理项id")
	private Long inspectionListId;

	@ApiModelProperty(value = "抽查项")
	private String spotCheckName;

	@ApiModelProperty(value = "抽查人")
	private String person;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "抽查时间")
	private Date selectDate;

	@ApiModelProperty(value = "抽查时限")
	private Date selectLimitDate;

	@ApiModelProperty(value = "上报时间")
	private Date seatDate;

	@ApiModelProperty(value = "图片")
	private String picture;

	@ApiModelProperty(value = "治理项id")
	private Long companyInspectionId;


	@ApiModelProperty(value = "抽查前图片")
	private List<String> pictureBefore;

	@ApiModelProperty(value = "抽查后图片")
	private List<String> pictureAfter;

	@ApiModelProperty(value = "页码")
	private String rownum;

}