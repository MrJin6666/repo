package com.goboosoft.modules.companymanagelog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_manage_log")
public class CompanyManageLogEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 审核结果
     */
	private String result;
    /**
     * 原因
     */
	private String cause;
    /**
     * 审核人
     */
	private Long auditUser;

	private long companyId;
	/**
	 * 类型
	 */
	private String type;
}