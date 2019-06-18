package com.goboosoft.company.managesystem.dto;

import com.goboosoft.company.manageread.dto.ManageReadDTO;
import com.goboosoft.system.sys.dto.SysUserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-08
 */
@Data
@ApiModel(value = "")
public class CompanyManageSystemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "公告ID")
	private Long id;

	@ApiModelProperty(value = "通告名称")
	private String noticeName;

	@ApiModelProperty(value = "上传人姓名")
	private String creatorName;

	@ApiModelProperty(value = "通告内容")
	private String noticeContent;

	@ApiModelProperty(value = "附件（上传图片）")
	private String attachment;

	@ApiModelProperty(value = "是否是企业（0：企业，1：部门）")
	private Integer isCompany;

	@ApiModelProperty(value = "图标")
	private String iconUri;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	private String name;

	private List<ManageReadDTO> manageReadDTOList;

	private List<SysUserDTO> userDTOList;

	private Long deptId;

	private Integer type;

	private Long companyId;

	private String rownum;

}