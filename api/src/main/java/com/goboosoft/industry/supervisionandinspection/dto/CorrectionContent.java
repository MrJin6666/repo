package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "整改项/检查项 内容")
public class CorrectionContent implements Serializable {
    private static final long serialVersionUID = 1L;

		@ApiModelProperty(value = "id")
		private Long id;

		@ApiModelProperty(value = "检查内容名称")
		private String inspectionListName;

		@ApiModelProperty(value = "检查状态")
		private Integer status;
}