package com.goboosoft.company.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 整改明细信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Data
@ApiModel(value = "业务 - 整改明细信息表")
public class BusinInspectionCorrectionMxDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "所属整改信息主键")
	private Long inspectionCorrectionId;

	@ApiModelProperty(value = "所属检查项主键")
	private Long inspectionTaskItemId;

	@ApiModelProperty(value = "所属检查项名称")
	private String inspectionTaskItemName;

	@ApiModelProperty(value = "问题描述")
	private String problemDescription;

	@ApiModelProperty(value = "问题图片")
	private String photos;

	@ApiModelProperty(value = "检查内容代码")
	private Long inspectionListId;

	@ApiModelProperty(value = "检查内容名称")
	private String inspectionListName;

	@ApiModelProperty(value = "状态 0新建  1待审核 2审核通过 3审核失败")
	private String correctionStatus;

	@ApiModelProperty(value = "整改完成时间")
	private Date correctionDate;

	@ApiModelProperty(value = "整改描述")
	private String finishRemark;

	@ApiModelProperty(value = "整改图片")
	private String finishPhotos;

	@ApiModelProperty(value = "整改人签名")
	private String underwrite;

	@ApiModelProperty(value = "复查描述")
	private String checkRemark;

	@ApiModelProperty(value = "复查图片")
	private String checkPhotos;

	@ApiModelProperty(value = "复查人签名")
	private String checkUnderwrite;

	@ApiModelProperty(value = "复查时间")
	private Date checkDate;




}