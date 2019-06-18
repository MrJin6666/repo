package com.goboosoft.modules.basicwarncolor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 过期警告颜色
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@ApiModel(value = "过期警告颜色")
public class BasicWarnColorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "超期时间 天")
	private Long beyond;

	@ApiModelProperty(value = "提醒颜色")
	private String color;

	@ApiModelProperty(value = "")
	private Date createDate;

	@ApiModelProperty(value = "创建人")
	private Long creator;


}