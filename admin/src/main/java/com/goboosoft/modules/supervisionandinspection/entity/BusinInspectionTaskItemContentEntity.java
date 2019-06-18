package com.goboosoft.modules.supervisionandinspection.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务 - 督导检查任务项内容信息表
 *
 * @author yflong DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_task_item_content")
public class BusinInspectionTaskItemContentEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 督导检查任务项主键
     */
	private Long inspectionTaskItemId;
    /**
     * 检查内容代码
     */
	private Long inspectionListId;
    /**
     * 检查内容名称
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
     * 合格状态 0不合格 1合格
     */
	private String passStatus;
	/**
	 * 评价
	 */
	private String remark;
	/**
	 * 图片
	 */
	private String photos;
}