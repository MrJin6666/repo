package com.goboosoft.company.govern.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("govern_process_plan")
public class GovernProcessPlanEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 治理计划id
     */
	private Long governPlanId;
    /**
     * 图片
     */
	private String picture;
    /**
     * 过程 1.待处理 2.处理中 3.已完成 4.超期
     */
	private Integer process;
    /**
     * 治理描述
     */
	private String describ;
    /**
     * 管理员签字
     */
	private String sign;
    /**
     * 抽查时间
     */
	private Date selectDate;
    /**
     * 抽查时限
     */
	private Date selectLimitDate;
}