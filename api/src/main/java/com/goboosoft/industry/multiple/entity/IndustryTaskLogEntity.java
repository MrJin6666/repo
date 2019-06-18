package com.goboosoft.industry.multiple.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 行业任务日志记录
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("industry_task_log")
public class IndustryTaskLogEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
	private Long industryTaskId;

    /**
     * 状态码:0:创建 1:指派 2:已完成
     */
	private Integer status;

    /**
     * 接收人id
     */
	private Long receiverId;
    /**
     * 描述
     */
	private String remark;
    /**
     * 处理描述
     */
	private String finishRemark;
    /**
     * 附件
     */
	private String accessory;
}