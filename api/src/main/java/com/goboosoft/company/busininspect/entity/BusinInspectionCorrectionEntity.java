package com.goboosoft.company.busininspect.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_correction")
public class BusinInspectionCorrectionEntity {
	private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
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
     * 状态 0新建  1待审核 2审核通过 3审核失败
     */
	private Integer status;
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
    /**
     * 整改人id
     */
	private Long correctionUserId;
    /**
     * 整改人名称
     */
	private String correctionUserName;
    /**
     * 复查人id
     */
	private Long checkUserId;
    /**
     * 复查人名称
     */
	private String checkUserName;
    /**
     * 复查时间
     */
	private Date checkDate;
    /**
     * 上次整改Id
     */
	private Long pid;
	/**
	 * 复查人签名
	 */
	private String checkUnderwrite;
	/**
	 * 整改人签名
	 */
	private String correctionUnderwrite;
}