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
@ApiModel(value = "检查报告详情")
public class ReportInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	    @ApiModelProperty(value = "分数")
	    private Integer score;

	    @ApiModelProperty(value = "是否合格")
		private Integer passStatus;

		@ApiModelProperty(value = "单号")
		private String tickets;

		@ApiModelProperty(value = "检查人")
	    private String inspectUserName;

		@ApiModelProperty(value = "检查时间")
		private Date inspectDate;

		@ApiModelProperty(value = "完成时间")
		private Date finishDate;

		@ApiModelProperty(value = "整改人名称")
		private String correctionUserName;

		@ApiModelProperty(value = "复查人名称")
		private String checkUserName;

		@ApiModelProperty(value = "检查人签名")
		private String inspectUnderwrite;

		@ApiModelProperty(value = "整改人签名")
		private String correctionUnderwrite;

		@ApiModelProperty(value = "复查人签名")
		private String checkUnderwrite;
}