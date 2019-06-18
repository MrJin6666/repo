package com.goboosoft.company.managesystem.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_manage_system")
public class CompanyManageSystemEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 通告名称
     */
	private String noticeName;
	/**
	 * 通告内容
	 */
	private String noticeContent;
	/**
	 * 通告内容
	 */
	private String creatorName;
//
    /**
     * 附件（上传图片）
     */
	private String attachment;
    /**
     * 是否是企业（0：企业，1：部门）
     */
	private Integer isCompany = 0;
    /**
     * 图标
     */
	private String iconUri;
	/**
	 * 状态 0：可用  1：不可用
	 */
	private Integer state = 0;
	/**
	 * 部门id
	 */
	private Long deptId;

	private Integer type;
	/**
	 * 企业id
	 */
	private Long companyId;
}