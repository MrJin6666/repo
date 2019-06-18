package com.goboosoft.company.busininspect.dto;

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

	@ApiModelProperty(value = "状态 0新建  1待审核 2审核通过 3审核失败")
	private Integer status;

	@ApiModelProperty(value = "检查人姓名，多个使用,分割")
	private String inspectionUserName;

	@ApiModelProperty(value = "检查时间")
	private Date inspectionDate;

	@ApiModelProperty(value = "改正时间")
	private Date correctionDate;

	@ApiModelProperty(value = "最晚整改时间")
	private Date lastCorrectionDate;

	@ApiModelProperty(value = "整改人id")
	private Long correctionUserId;

	@ApiModelProperty(value = "整改人名称")
	private String correctionUserName;

	@ApiModelProperty(value = "复查人id")
	private Long checkUserId;

	@ApiModelProperty(value = "复查人名称")
	private String checkUserName;

	@ApiModelProperty(value = "复查时间")
	private Date checkDate;

	@ApiModelProperty(value = "上次整改Id")
	private Long pid;

	@ApiModelProperty(value = "复查人签名")
	private String checkUnderwrite;

	@ApiModelProperty(value = "整改人签名")
	private String correctionUnderwrite;

}