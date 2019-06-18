package com.goboosoft.modules.basicmeasure.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 计量单位
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@ApiModel(value = "计量单位")
public class BasicMeasureDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "计量单位名称")
	private String name;

	@ApiModelProperty(value = "单位简写")
	private String logogram;

	@ApiModelProperty(value = "说明")
	private String instructions;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "创建人")
	private Long creator;


}