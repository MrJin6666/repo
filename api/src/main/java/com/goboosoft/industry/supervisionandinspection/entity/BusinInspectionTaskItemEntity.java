package com.goboosoft.industry.supervisionandinspection.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务 - 督导检查任务项信息表
 *
 * @author yflong
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_task_item")
public class BusinInspectionTaskItemEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;

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
     * 状态 0新建 1完成
     */
	private Integer status;
    /**
     * 合格状态 0不合格 1合格
     */
	private Integer passStatus;

}