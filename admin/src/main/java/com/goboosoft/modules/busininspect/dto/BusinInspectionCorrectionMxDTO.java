package com.goboosoft.modules.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 整改明细信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
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

	@ApiModelProperty(value = "")
	private String problemDescription;

	@ApiModelProperty(value = "检查内容代码")
	private Long inspectionListId;

	@ApiModelProperty(value = "检查内容名称")
	private String inspectionListName;

	@ApiModelProperty(value = "整改状态 0待整改 1已完成")
	private String correctionStatus;

	@ApiModelProperty(value = "整改完成时间")
	private Date correctionDate;

	@ApiModelProperty(value = "问题图片")
	private String photos;


}