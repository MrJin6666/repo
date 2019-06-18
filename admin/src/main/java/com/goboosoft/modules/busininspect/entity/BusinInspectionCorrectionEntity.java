package com.goboosoft.modules.busininspect.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务 - 整改基本信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_correction")
public class BusinInspectionCorrectionEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 整改企业主键
     */
	private Long companyId;
    /**
     * 整改企业名称
     */
	private String companyName;
    /**
     * 所属检查任务主键
     */
	private Long inspectionTaskId;
    /**
     * 合格状态 0未改正 1已改正
     */
	private String correctionStatus;
    /**
     * 检查人姓名，多个使用","分割
     */
	private String inspectionUserName;
    /**
     * 检查时间
     */
	private Date inspectionDate;
    /**
     * 改正时间
     */
	private Date correctionDate;
    /**
     * 最晚整改时间
     */
	private Date lastCorrectionDate;
}