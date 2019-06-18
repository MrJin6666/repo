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
@ApiModel(value = "待复查列表")
public class CheckListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "rownum")
	private Integer rownum;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "整改企业名称")
	private String companyName;

	@ApiModelProperty(value = "所属检查任务主键")
	private Long inspectionTaskId;

	@ApiModelProperty(value = "单号")
	private String tickets;

	@ApiModelProperty(value = "整改人")
	private String correctionUserName;

	@ApiModelProperty(value = "整改时间")
	private Date correctionDate;

	@ApiModelProperty(value = "检查人姓名，多个使用,分割")
	private String inspectionUserName;

	@ApiModelProperty(value = "检查日期")
	private Date inspectionDate;

}