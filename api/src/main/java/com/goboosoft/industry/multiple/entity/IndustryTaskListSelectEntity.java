package com.goboosoft.industry.multiple.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("industry_task_list")
public class IndustryTaskListSelectEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
	private String title;
    /**
     * 接收人主键
     */
	private Long receiverId;
    /**
     * 接收人
     */
	private String receiver;
    /**
     * 最晚完成时间
     */
	private Date latestCompletionTime;
	/**
	 * 订单号
	 */
	private String orderNumber;
	/**
	 * 状态码
	 */
	private Integer status;

	private Long fcDeptId;

	private Long jsDeptId;
}