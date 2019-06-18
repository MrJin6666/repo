package com.goboosoft.system.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 基础 - 公司信息表
 *
 * @author DMXUAN ${email}
 * @since 1.0.0 2019-03-18
 */
@Data
@ApiModel(value = "基础 - 公司信息表")
public class SysCompanyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "公司/单位名称")
	private String name;

	@ApiModelProperty(value = "企业唯一编码")
	private String code;

	@ApiModelProperty(value = "地址 - 省")
	private String adrProvince;

	@ApiModelProperty(value = "地址 - 市")
	private String adrCity;

	@ApiModelProperty(value = "区")
	private String adrArea;

	@ApiModelProperty(value = "地址 - 街道")
	private String adrStreet;

	@ApiModelProperty(value = "地址 - 社区")
	private String adrCommunity;

	@ApiModelProperty(value = "地址 - 路，门牌号")
	private String adrRoad;

	@ApiModelProperty(value = "")
	private String lng;

	@ApiModelProperty(value = "纬度")
	private String lat;

	@ApiModelProperty(value = "状态 0待审核 1审核通过 2 审核不通过")
	private Integer status;

	@ApiModelProperty(value = "公司类型 0企业 1行业")
	private String companyType;

	@ApiModelProperty(value = "现管单位id")
	private Long xgdw;

	@ApiModelProperty(value = "创建人主键")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "修改人主键")
	private Long updater;

	@ApiModelProperty(value = "修改时间")
	private Date updateDate;

	@ApiModelProperty(value = "检查项ids")
	private String listIds;

	@ApiModelProperty(value = "联系人")
	private String linkman;

	@ApiModelProperty(value = "地址")
	private String address;

	@ApiModelProperty(value = "发证机关")
	private String issuingAuthority;

	@ApiModelProperty(value = "营业执照")
	private String licence;

	@ApiModelProperty(value = "联系人电话")
	private String linkmanPhone;

	@ApiModelProperty(value = "原因")
	private String cause;

	@ApiModelProperty(value = "行业检查状态：0红牌,1黄牌,2,一般，3正常")
	private String checkStatus;

	@ApiModelProperty(value = "行业检查人id")
	private Long userId;

	@ApiModelProperty(value = "是否停业")
	private Integer resultIsClosed;


}