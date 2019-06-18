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
@ApiModel(value = "任务信息列表")
public class BusinInspectionTaskListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "rownum")
	private Integer rownum;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "检查人主键")
	private Long inspectUserId;

	@ApiModelProperty(value = "检查人名称")
	private String inspectUserName;

	@ApiModelProperty(value = "督导日期")
	private Date inspectDate;

	@ApiModelProperty(value = "")
	private Long creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "单号")
	private String tickets;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "企业名称")
	private String companyName;

	@ApiModelProperty(value = "企业id")
	private Long companyId;

	@ApiModelProperty(value = "现场照片")
	private String scenePhoto;

}