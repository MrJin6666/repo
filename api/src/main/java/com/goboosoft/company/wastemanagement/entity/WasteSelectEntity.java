package com.goboosoft.company.wastemanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 危废 选项
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("waste_select")
public class WasteSelectEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 1.危废类型 2.危废名称 3.计量单位 4.废物来源 5.危废保存位置 6.接收单位 7.危废去向 8.所属部门
     */
	private Integer type;
    /**
     * 企业id
     */
	private Long companyId;

	private String name;
}