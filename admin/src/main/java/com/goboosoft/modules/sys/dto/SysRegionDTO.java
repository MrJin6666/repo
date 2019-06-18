package com.goboosoft.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "")
public class SysRegionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Double id;

	@ApiModelProperty(value = "")
	private String name;

	@ApiModelProperty(value = "")
	private Integer level;

	@ApiModelProperty(value = "")
	private Double parentId;


}