package com.goboosoft.company.busininspect.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务 - 整改明细信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_correction_mx")
public class BusinInspectionCorrectionMxEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
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
     * 问题描述
     */
	private String problemDescription;
    /**
     * 问题图片
     */
	private String photos;
    /**
     * 检查内容代码
     */
	private Long inspectionListId;
    /**
     * 检查内容名称
     */
	private String inspectionListName;
    /**
     * 状态 0新建  1待审核 2审核通过 3审核失败
     */
	private String correctionStatus;
    /**
     * 整改完成时间
     */
	private Date correctionDate;
    /**
     * 整改描述
     */
	private String finishRemark;
    /**
     * 整改图片
     */
	private String finishPhotos;
    /**
     * 整改人签名
     */
	private String underwrite;
    /**
     * 复查描述
     */
	private String checkRemark;
    /**
     * 复查图片
     */
	private String checkPhotos;
    /**
     * 复查人签名
     */
	private String checkUnderwrite;
	/**
	 * 复查时间
	 */
	private Date checkDate;
}