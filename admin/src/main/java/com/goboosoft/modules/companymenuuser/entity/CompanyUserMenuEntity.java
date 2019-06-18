package com.goboosoft.modules.companymenuuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_user_menu")
public class CompanyUserMenuEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
	private Long userId;
    /**
     * 企业菜单id
     */
	private String companyMenuModule;
}