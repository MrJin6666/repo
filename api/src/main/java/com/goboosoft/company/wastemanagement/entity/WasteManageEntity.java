package com.goboosoft.company.wastemanagement.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("company_waste_manage")
public class WasteManageEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 危废类型
     */
    private String wasteType;
    /**
     * 危废名称
     */
    private String wasteName;
    /**
     * 计数
     */
    private Integer count;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 危废来源
     */
    private String wasteSource;
    /**
     * 危废去向
     */
    private String wasteDirection;
    /**
     * 保存位置
     */
    private String saveLocation;
    /**
     * 更新人名称
     */
    private String inName;
    /**
     * 出库人名称
     */
    private String outName;
    /**
     * 入库或出库（0：入库 1：出库）
     */
    private Integer inboundOrOutbound;
    /**
     * 出库单号
     */
    private String outboundNum;
    /**
     * 出库时间
     */
    private Date outboundDate;
    /**
     * 入库人签字
     */
    private String inSignature;
    /**
     * 出库人签字
     */
    private String outSignature;
    /**
     * 接收单位签字
     */
    private String unitSignature;
    /**
     * 接收单位
     */
    private String receiverUnit;
    /**
     * 接收单位  所属管理部门
     */
    private String belongDept;
    /**
     * 转移联单
     */
    private String transfer;
    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 是否确认（0：未确认 1：确认）
     */
    private Integer isVerify;
    /**
     * 管理员名称
     */
    private String admin;
    /**
     * 入库管理员签字
     */
    private String adminSignature;
    /**
     * 入库管理员确认时间
     */
    private Date verifyDate;

    private Integer balanceNum;
}