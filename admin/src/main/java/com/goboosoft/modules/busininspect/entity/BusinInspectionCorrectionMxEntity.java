package com.goboosoft.modules.busininspect.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务 - 整改明细信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_correction_mx")
public class BusinInspectionCorrectionMxEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 所属整改信息主键
     */
	private Long inspectionCorrectionId;
    /**
     * 所属检查项主键
     */
	private Long inspectionTaskItemId;
    /**
     * 所属检查项名称
     */
	private String inspectionTaskItemName;
    /**
     * 
     */
	private String problemDescription;
    /**
     * 检查内容代码
     */
	private Long inspectionListId;
    /**
     * 检查内容名称
     */
	private String inspectionListName;
    /**
     * 整改状态 0待整改 1已完成
     */
	private String correctionStatus;
    /**
     * 整改完成时间
     */
	private Date correctionDate;
    /**
     * 问题图片
     */
	private String photos;
}