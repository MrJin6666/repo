package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 行业任务日志记录
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Data
@ApiModel(value = "行业任务日志记录")
public class IndustryTaskLogProcessDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日志生成时间")
	private String logDate;

	@ApiModelProperty(value = "状态码:0:创建 1:指派 2:已完成")
	private Integer status;



}