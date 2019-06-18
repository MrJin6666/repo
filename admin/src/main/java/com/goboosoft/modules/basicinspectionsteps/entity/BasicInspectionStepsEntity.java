package com.goboosoft.modules.basicinspectionsteps.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("basic_inspection_steps")
public class BasicInspectionStepsEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 治理步骤名称
     */
	private String name;
    /**
     * 治理顺序
     */
	private Long sort;

	private Long inspectionListId;
}