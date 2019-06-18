package com.goboosoft.modules.industrytasklist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("industry_task_list")
public class IndustryTaskListEntity extends BaseEntity {
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
     * 最晚完成时间
     */
	private Date latestCompletionTime;
    /**
     * 描述
     */
	private String description;

    /**
     * 附件
     */
	private String accessory;
    /**
     * 订单号
     */
	private String orderNumber;
    /**
     * 创建时间
     */
	private Date createDate;
    /**
     * 状态码：0.创建1.过程中2.已完成
     */
	private Integer status;
}