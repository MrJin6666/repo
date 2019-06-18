package com.goboosoft.modules.supervisionandinspection.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务 - 督导检查任务项信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_task_item")
public class BusinInspectionTaskItemEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 检查项名称
     */
	private Long inspectionTaskId;
    /**
     * 检查项代码
     */
	private Long inspectionListId;
    /**
     * 检查项名称
     */
	private String inspectionListName;
    /**
     * 本项得分
     */
	private Integer score;
    /**
     * 本项满分
     */
	private Integer totalScore;
    /**
     * 描述
     */
	private String description;
    /**
     * 状态 0新建 1完成
     */
	private Integer status;
    /**
     * 合格状态 0不合格 1合格
     */
	private Integer passStatus;
    /**
     * 来源 : 1 任务 2 修改
     */
	private String source;
}