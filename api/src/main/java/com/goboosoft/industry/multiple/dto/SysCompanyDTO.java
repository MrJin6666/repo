package com.goboosoft.industry.multiple.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.goboosoft.system.sys.dto.SysDeptDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-02-11
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_company")
public class SysCompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
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

	@ApiModelProperty(value = "地址 - 省")
	private String lng;

	@ApiModelProperty(value = "纬度")
	private String lat;

	@ApiModelProperty(value = "状态 0待审核 1 审核通过 2 审核不通过")
	private Integer status;

	@ApiModelProperty(value = "企业是否停业状态")
	private Integer resultIsClosed;

	@ApiModelProperty(value = "公司类型 0行业 1企业")
	private String companyType;

	@ApiModelProperty(value = "现管单位")
	private Long xgdw;

	@ApiModelProperty(value = "修改人主键")
	private Long updater;

	@ApiModelProperty(value = "修改时间")
	private Date updateDate;

	@ApiModelProperty(value = "检查项ids")
	private String listIds;

	@ApiModelProperty(value = "联系人")
	private String linkman;

	@ApiModelProperty(value = "联系人电话")
	private String linkmanPhone;

	@ApiModelProperty(value = "地址")
	private String address;

	@ApiModelProperty(value = "发证机关")
	private String issuingAuthority;

	@ApiModelProperty(value = "营业执照")
	private String licence;

	@ApiModelProperty(value = "原因")
	private String cause;

	@ApiModelProperty(value = "行业检查状态")
	private String checkStatus;

	@ApiModelProperty(value = "行业检查人id")
	private Long userId;

	private SysDeptDTO sysDeptDTO;

	private SysUserDTO sysUserDTO;

	private String province;

	private String city;

	private String area;

}