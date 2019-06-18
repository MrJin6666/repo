package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "业务 - 检查任务信息表")
public class InspectionTaskInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "企业名称")
	private String companyName;

	@ApiModelProperty(value = "检查人名称")
	private String inspectUserName;

	@ApiModelProperty(value = "随行人员名称")
	private String accompanyUserNames;

	@ApiModelProperty(value = "督导日期")
	private Date inspectDate;

	@ApiModelProperty(value = "单号")
	private String tickets;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "签名")
	private String underwrite;

	@ApiModelProperty(value = "整改时限")
	private Date lastCorrectionDate;

	@ApiModelProperty(value = "检查项")
	private List<InspectionInfoDTO> inspectionInfoDTOS;
}