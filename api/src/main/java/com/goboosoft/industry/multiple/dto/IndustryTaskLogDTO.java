package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 行业任务日志记录
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Data
@ApiModel(value = "行业任务日志记录")
public class IndustryTaskLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "任务Id")
	private Long industryTaskId;

	@ApiModelProperty(value = "接收人id")
	private Long receiverId;

	@ApiModelProperty(value = "状态码:0:创建 1:指派 2:已完成")
	private Integer status;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate  ;

	@ApiModelProperty(value = "描述")
	private String remark  ;

	@ApiModelProperty(value = "处理描述")
	private String finishRemark  ;

	@ApiModelProperty(value = "附件")
	private String accessory  ;

	@ApiModelProperty(value = "附件解析")
	private List<String> accessoryList;

}