package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "检查任务信息评价")
public class InspectionTaskReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "督查任务主键")
	private Long id;

	@ApiModelProperty(value = "红黄牌状态")
	private String checkStatus;

	@ApiModelProperty(value = "是否停业")
	private Integer isClosed;

	@ApiModelProperty(value = "最晚整改时间")
	private Date lastCorrectionDate;

	@ApiModelProperty(value = "签名")
	private String underwrite;

}