package com.goboosoft.modules.supervisionandinspection.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 业务 - 检查任务信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("busin_inspection_task")
public class BusinInspectionTaskEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 企业主键
     */
	private Long companyId;
    /**
     * 企业名称
     */
	private String companyName;
    /**
     * 检查人主键
     */
	private Long inspectUserId;
    /**
     * 检查人名称
     */
	private String inspectUserName;
    /**
     * 随行人员主键
     */
	private String accompanyUserIds;
    /**
     * 随行人员名称
     */
	private String accompanyUserNames;
    /**
     * 督导日期
     */
	private Date inspectDate;
    /**
     * 现场照片
     */
	private String scenePhoto;
    /**
     * 整改人主键
     */
	private Long correctionUserId;
    /**
     * 整改人名称
     */
	private String correctionUserName;
    /**
     * 合格状态 0不合格 1合格
     */
	private String passStatus;
    /**
     * 
     */
	private Long updater;
    /**
     * 
     */
	private Date updateDate;
    /**
     * 单号
     */
	private String tickets;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 签名
	 */
	private String underwrite;
	/**
	 * 检查红黄牌状态
	 */
	private Integer checkStatus;
	/**
	 * 是否停业 0是 1否
	 */
	private Integer resultIsClosed;

}