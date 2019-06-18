package com.goboosoft.company.companymanagelog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
	/**
	 * 公司Id
	 */
	private Long companyId;

	private String type;
}