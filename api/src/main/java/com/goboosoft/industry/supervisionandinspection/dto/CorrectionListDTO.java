package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Data
@ApiModel(value = "待整改列表")
public class CorrectionListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "rownum")
	private Integer rownum;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "单号")
	private String tickets;

	@ApiModelProperty(value = "整改企业名称")
	private String companyName;

	@ApiModelProperty(value = "所属检查任务主键")
	private Long inspectionTaskId;

	@ApiModelProperty(value = "0 超时 1正常")
	private Integer status;

	@ApiModelProperty(value = "检查人姓名，多个使用,分割")
	private String inspectionUserName;

	@ApiModelProperty(value = "检查时间")
	private Date inspectionDate;

	@ApiModelProperty(value = "最晚整改时间")
	private Date lastCorrectionDate;


}