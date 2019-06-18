package com.goboosoft.modules.industrytasklist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
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

	@ApiModelProperty(value = "最晚完成时间")
	private Date latestCompletionTime;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "附件")
	private String accessory;

	@ApiModelProperty(value = "订单号")
	private String orderNumber;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "状态码：0.创建1.过程中2.已完成")
	private Integer status;

	@ApiModelProperty(value = "根据id接收人")
	private String name;

}