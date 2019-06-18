package com.goboosoft.modules.basicmeasure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 计量单位
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("basic_measure")
public class BasicMeasureEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 计量单位名称
     */
	private String name;
    /**
     * 单位简写
     */
	private String logogram;
    /**
     * 说明
     */
	private String instructions;
}