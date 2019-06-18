package com.goboosoft.modules.basicinspectionlist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("basic_inspection_list")
public class BasicInspectionListEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 检查项/检查内容名称
     */
	private String name;
    /**
     * 级别 0检查项 1检查内容
     */
	private String level;
    /**
     * 上级主键
     */
	private Long pid;
    /**
     * 总分
     */
	private Integer totalScore;

	private long cycle;

	private String details;

	private String contentType;

	private long aheadDay;
}