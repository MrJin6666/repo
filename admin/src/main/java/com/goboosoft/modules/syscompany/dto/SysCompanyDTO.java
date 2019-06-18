package com.goboosoft.modules.syscompany.dto;

import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogOneDTO;
import com.goboosoft.modules.conmpanyinspect.dto.ConmpanyInspectDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
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

	@ApiModelProperty(value = "状态 0待审核 1审核通过")
	private String status;

	@ApiModelProperty(value = "公司类型 0行业 1企业")
	private String companyType;

	@ApiModelProperty(value = "现管单位")
	private long xgdw;

	@ApiModelProperty(value = "创建人主键")
	private Long creator;

	@ApiModelProperty(value = "监管部门负责人")
	private Long userId;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "修改人主键")
	private Long updater;

	@ApiModelProperty(value = "修改时间")
	private Date updateDate;

	@ApiModelProperty(value = "监管部门名称")
	private String deptName;

	@ApiModelProperty(value = "责任人")
	private String principalName;

	@ApiModelProperty(value = "联系电话")
	private String linkmanPhone;

	@ApiModelProperty(value = "联系人")
	private String linkman;

	@ApiModelProperty(value = "发证机关")
	private String issuingAuthority;

	@ApiModelProperty(value = "营业执照")
	private String licence;

	@ApiModelProperty(value = "地址")
	private String address;

	@ApiModelProperty(value = "烤漆房数量")
	private long pkqfNum;

	@ApiModelProperty(value = "调漆间数量")
	private long tqNum;

	@ApiModelProperty(value = "打磨间数量")
	private long dmNum;

	@ApiModelProperty(value = "危废间数量")
	private long wfNum;

	@ApiModelProperty(value = "审核意见")
	private String cause;

	@ApiModelProperty(value = "分类")
	private List<ConmpanyInspectDTO> conmpanyInspectDTO;
	@ApiModelProperty(value = "日志列表")
	private List<CompanyManageLogOneDTO> companyManageLogOneDTOList;

}