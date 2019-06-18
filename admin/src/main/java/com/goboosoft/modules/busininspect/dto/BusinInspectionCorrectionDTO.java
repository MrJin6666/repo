package com.goboosoft.modules.busininspect.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 整改基本信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Data
@ApiModel(value = "业务 - 整改基本信息表")
public class BusinInspectionCorrectionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "整改企业主键")
	private Long companyId;

	@ApiModelProperty(value = "整改企业名称")
	private String companyName;

	@ApiModelProperty(value = "所属检查任务主键")
	private Long inspectionTaskId;

	@ApiModelProperty(value = "合格状态 0未改正 1已改正")
	private String correctionStatus;

	@ApiModelProperty(value = "检查人姓名，多个使用,分割")
	private String inspectionUserName;

	@ApiModelProperty(value = "检查时间")
	private Date inspectionDate;

	@ApiModelProperty(value = "改正时间")
	private Date correctionDate;

	@ApiModelProperty(value = "最晚整改时间")
	private Date lastCorrectionDate;


}