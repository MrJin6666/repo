package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
@ApiModel(value = "行业 - 新任务发布表")
public class IndustryTaskListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "接收人主键")
	private Long receiverId;

	@ApiModelProperty(value = "接收人")
	private String receiver;

	@ApiModelProperty(value = "最晚完成时间")
	private Date latestCompletionTime;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "附件")
	private String accessory;

	@ApiModelProperty(value = "附件解析")
	private List<String> accessoryList;

	@ApiModelProperty(value = "订单号")
	private String orderNumber;

	@ApiModelProperty(value = "任务当前状态")
	private Integer status;

	@ApiModelProperty(value = "操作记录")
	private List<IndustryTaskLogDTO> logList;

	@ApiModelProperty(value = "发出人部门")
	private Long fcDeptId;

	@ApiModelProperty(value = "接收人部门")
	private Long jsDeptId;

}