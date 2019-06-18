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
@ApiModel(value = "复查结果保存")
public class CheckSaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	    @ApiModelProperty(value = "id")
	    private Long id;

		@ApiModelProperty(value = "复查结果 0不通过 1通过")
		private Integer checkResult;

		@ApiModelProperty(value = "复查描述")
		private String remark;

		@ApiModelProperty(value = "复查图片")
		private String photos;

		@ApiModelProperty(value = "复查人签名")
		private String underwrite;
}