package com.goboosoft.company.companymenuuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_menu")
public class CompanyMenuEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 菜单类型
     */
	private String type;
    /**
     * 菜单名称
     */
	private String module;
	/**
	 * 名称
	 */
	private String name;
}