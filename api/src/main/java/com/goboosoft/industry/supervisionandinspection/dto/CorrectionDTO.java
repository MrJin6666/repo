package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "整改项以及内容")
public class CorrectionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

		@ApiModelProperty(value = "id")
		private Long id;

		@ApiModelProperty(value = "单号")
		private String tickets;

		@ApiModelProperty(value = "项目名")
		private String name;

		@ApiModelProperty(value = "检查结论")
		private String check = "不合格";

		@ApiModelProperty(value = "检查人")
		private String inspectionUserName;

		@ApiModelProperty(value = "最晚整改时间")
		private Date lastCorrectionDate;

		@ApiModelProperty(value = "问题描述")
		private String problemDescription;

		@ApiModelProperty(value = "问题图片")
		private String photos;

		@ApiModelProperty(value = "检查人签名")
		private String underwrite;
}