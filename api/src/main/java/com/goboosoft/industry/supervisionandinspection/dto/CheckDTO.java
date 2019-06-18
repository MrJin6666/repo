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
@ApiModel(value = "检查项以及内容")
public class CheckDTO implements Serializable {
    private static final long serialVersionUID = 1L;

		@ApiModelProperty(value = "id")
		private Long id;

		@ApiModelProperty(value = "项目名称")
		private String name;

		@ApiModelProperty(value = "整改人名称")
		private String correctionUserName;

		@ApiModelProperty(value = "整改时间")
		private Date correctionDate;

		@ApiModelProperty(value = "整改描述")
		private String finishRemark;

		@ApiModelProperty(value = "整改图片")
		private String finishPhotos;

		@ApiModelProperty(value = "整改人签名")
		private String underwrite;
}