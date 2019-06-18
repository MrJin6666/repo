package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
@ApiModel(value = "行业 - 新任务发布表")
public class IndustryTaskListSelectDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "任务Id")
	private Long id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "接收人主键")
	private Long receiverId;

	@ApiModelProperty(value = "接收人名称")
	private String receiverName;

	@ApiModelProperty(value = "接收人手机号")
	private String receiverMobile;

	@ApiModelProperty(value = "发出人Id")
	private Long creator;

	@ApiModelProperty(value = "发出人手机号")
	private String creatorMobile;

	@ApiModelProperty(value = "发出人名称")
	private String creatorName;

	@ApiModelProperty(value = "最晚完成时间")
	private Date latestCompletionTime;

	@ApiModelProperty(value = "订单号")
	private String orderNumber;

	@ApiModelProperty(value = "状态码")
	private Integer status;

	@ApiModelProperty(value = "页码")
	private String rownum;

	@ApiModelProperty(value = "发出人部门")
	private Long fcDeptId;

	@ApiModelProperty(value = "接收人部门")
	private Long jsDeptId;

	@ApiModelProperty(value = "是否超期 0 否 1是")
	private Integer isoverdue;

	@ApiModelProperty(value = "是否有驳回及确认权限 null 无 1 有")
	private Integer isDismissed;
}