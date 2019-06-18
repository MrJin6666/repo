
package com.goboosoft.system.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件
 *
 * @author DMXUAN
 * @since 1.0.0
 */
@Data
@ApiModel(value = "文件")
public class SysFileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "String流")
	private String inputStream;

	@ApiModelProperty(value = "文件名")
	private String originalFilename   ;

	@ApiModelProperty(value = "类型")
	private String contentType;

}