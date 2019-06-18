package com.goboosoft.modules.industrytasklist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 行业任务日志记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@ApiModel(value = "行业任务日志记录")
public class IndustryTaskLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "任务id")
	private Long industryTaskId;

	@ApiModelProperty(value = "状态码:0:创建 1:处理中 2:已完成")
	private Integer status;

	@ApiModelProperty(value = "附件")
	private String accessory;

	@ApiModelProperty(value = "描述")
	private String remark;

	@ApiModelProperty(value = "接收者")
	private Long receiverId;

	@ApiModelProperty(value = "处理描述")
	private String finishRemark;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "创建者")
	private String creator;


}