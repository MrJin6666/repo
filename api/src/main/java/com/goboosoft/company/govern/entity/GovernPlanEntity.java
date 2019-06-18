package com.goboosoft.company.govern.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("govern_plan")
public class GovernPlanEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 治理人
     */
	private String person;
    /**
     * 单号
     */
	private String code;
    /**
     * 治理时间
     */
	private Date seatDate;
    /**
     * 计划结束时间
     */
	private Date endDate;
    /**
     * 项目名称
     */
	private String name;
    /**
     * 治理状态(0 待处理)
     */
	private String status;
    /**
     * 计划状态 1 抽查 2 治理
     */
	private String planStatus;
    /**
     * 治理项id
     */
	private Long inspectionListId;
	/**
	 * 企业id
	 */
	private Long companyId;

	private Long companyInspectionId;
	/**
	 * 创建人部门id
	 */
	private Long creatorDeptId;


}