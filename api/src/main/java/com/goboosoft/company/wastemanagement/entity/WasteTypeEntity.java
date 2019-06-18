package com.goboosoft.company.wastemanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业危废管理内容类型
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_waste_type")
public class WasteTypeEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 危废类型集合
     */
	private String wasteTypeDir;
    /**
     * 危废名称集合
     */
	private String nameDir;
    /**
     * 计量单位集合
     */
	private String unitDir;
    /**
     * 废物来源集合
     */
	private String sourceDir;
    /**
     * 危废保存位置集合
     */
	private String saveDir;
    /**
     * 接收单位集合
     */
	private String receiveUnitDir;
	/**
	 * 所属部门集合
	 */
	private String deptDir;
    /**
     * 危废去向集合
     */
	private String directionDir;
    /**
     * 企业id
     */
	private Long companyId;
}