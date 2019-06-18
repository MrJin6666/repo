package com.goboosoft.modules.industrytasklist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 行业任务日志记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("industry_task_log")
public class IndustryTaskLogEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
	private Long industryTaskId;
    /**
     * 状态码:0:创建 1:处理中 2:已完成
     */
	private Integer status;
    /**
     * 附件
     */
	private String accessory;
    /**
     * 描述
     */
	private String remark;
    /**
     * 接收者
     */
	private Long receiverId;
    /**
     * 处理描述
     */
	private String finishRemark;
    /**
     * 创建时间
     */
	private Date createDate;
}