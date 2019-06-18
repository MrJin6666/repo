package com.goboosoft.system.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 基础 - 公司信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-18
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_company")
public class SysCompanyEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 公司/单位名称
     */
	private String name;
    /**
     * 企业唯一编码
     */
	private String code;
    /**
     * 地址 - 省
     */
	private String adrProvince;
    /**
     * 地址 - 市
     */
	private String adrCity;
    /**
     * 区
     */
	private String adrArea;
    /**
     * 地址 - 街道
     */
	private String adrStreet;
    /**
     * 地址 - 社区
     */
	private String adrCommunity;
    /**
     * 地址 - 路，门牌号
     */
	private String adrRoad;
    /**
     * 
     */
	private String lng;
    /**
     * 纬度
     */
	private String lat;
    /**
     * 状态 0待审核 1审核通过 2 审核不通过
     */
	private Integer status;
    /**
     * 公司类型 0企业 1行业
     */
	private String companyType;
    /**
     * 现管单位id
     */
	private Long xgdw;
    /**
     * 修改人主键
     */
	private Long updater;
    /**
     * 修改时间
     */
	private Date updateDate;
    /**
     * 检查项ids
     */
	private String listIds;
    /**
     * 联系人
     */
	private String linkman;
    /**
     * 地址
     */
	private String address;
    /**
     * 发证机关
     */
	private String issuingAuthority;
    /**
     * 营业执照
     */
	private String licence;
    /**
     * 联系人电话
     */
	private String linkmanPhone;
    /**
     * 原因
     */
	private String cause;
    /**
     * 行业检查状态：0红牌,1黄牌,2,一般，3正常
     */
	private String checkStatus;
    /**
     * 行业检查人id
     */
	private Long userId;
    /**
     * 是否停业
     */
	private Integer resultIsClosed;

}