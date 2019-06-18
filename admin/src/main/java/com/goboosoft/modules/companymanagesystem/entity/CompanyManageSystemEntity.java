package com.goboosoft.modules.companymanagesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
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
     * 创造者名称
     */
	private String creatorName;
    /**
     * 通告内容
     */
	private String noticeContent;
    /**
     * 附件（上传图片）
     */
	private String attachment;
    /**
     * 是否是企业（0：企业，1：部门）
     */
	private Integer isCompany;
    /**
     * 0：正常 1：不可用（删除数据时标记为“1”）
     */
	private Integer state;
    /**
     * 图标
     */
	private String iconUri;
    /**
     * 部门id
     */
	private Long deptId;

	private int type;
}