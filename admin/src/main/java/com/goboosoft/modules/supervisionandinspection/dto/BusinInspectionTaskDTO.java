package com.goboosoft.modules.supervisionandinspection.dto;

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
@ApiModel(value = "业务 - 检查任务信息表")
public class BusinInspectionTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "企业主键")
	private Long companyId;

	@ApiModelProperty(value = "企业名称")
	private String companyName;

	@ApiModelProperty(value = "检查人主键")
	private Long inspectUserId;

	@ApiModelProperty(value = "检查人名称")
	private String inspectUserName;

	@ApiModelProperty(value = "现场照片")
	private String scenePhoto;

	@ApiModelProperty(value = "随行人员主键")
	private String accompanyUserIds;

	@ApiModelProperty(value = "随行人员名称")
	private String accompanyUserNames;

	@ApiModelProperty(value = "督导日期")
	private Date inspectDate;

	@ApiModelProperty(value = "整改人主键")
	private Long correctionUserId;

	@ApiModelProperty(value = "整改人名称")
	private String correctionUserName;

	@ApiModelProperty(value = "合格状态 0不合格 1合格")
	private String passStatus;

	@ApiModelProperty(value = "")
	private Long creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "")
	private Long updater;

	@ApiModelProperty(value = "")
	private Date updateDate;

	@ApiModelProperty(value = "单号")
	private String tickets;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "签名")
	private String underwrite;

	@ApiModelProperty(value = "检查红黄牌状态")
	private Integer checkStatus;

	@ApiModelProperty(value = "是否停业 0是 1否 ")
	private Integer resultIsClosed;

}