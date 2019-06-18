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
@ApiModel(value = "检查报告")
public class ReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

		@ApiModelProperty(value = "rowunm")
		private Integer rowunm;

		@ApiModelProperty(value = "id")
		private Long id;

	    @ApiModelProperty(value = "整改企业名称")
		private String companyName;

		@ApiModelProperty(value = "部门名称")
		private String deptName;

		@ApiModelProperty(value = "检查时间")
		private Date inspectDate;

		@ApiModelProperty(value = "完成时间")
		private Date finishDate;

		@ApiModelProperty(value = "检查人")
		private String inspectUserName;

		@ApiModelProperty(value = "分数")
		private Integer score;

		@ApiModelProperty(value = "整改人名称")
		private String correctionUserName;
}