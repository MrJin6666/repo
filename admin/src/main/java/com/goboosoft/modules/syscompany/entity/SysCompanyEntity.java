package com.goboosoft.modules.syscompany.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
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
     * 状态 0待审核 1审核通过
     */
	private String status;
    /**
     * 公司类型 0行业 1企业
     */
	private String companyType;
    /**
     * 现管单位
     */
	private long xgdw;
    /**
     * 修改人主键
     */
	private Long updater;
    /**
     * 修改时间
     */
	private Date updateDate;

	private String linkmanPhone;

	private String linkman;

	private String issuingAuthority;

	private String licence;

	private String address;

	private String cause;

	private Long userId;
}