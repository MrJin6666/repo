package com.goboosoft.company.manageread.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 公告记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("manage_read")
public class ManageReadEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 已读人id
     */
	private Long finshRead;
    /**
     * 公告id
     */
	private Long manageSystemId;
    /**
     * 已读人姓名
     */
	private String finshReadName;
    /**
     * 0 企业 1 行业
     */
	private Integer isCompany;

	private String headUrl;

	private Long companyId;
}