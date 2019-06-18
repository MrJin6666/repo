package com.goboosoft.industry.multiple.dto;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class SpotCheckCompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "公司id")
	private Long companyId;

	@ApiModelProperty(value = "公司/单位名称")
	private String companyName;

	@ApiModelProperty(value = "联系人")
	private String linkMan;

	@ApiModelProperty(value = "联系人电话")
	private String linkManPhone;

	@ApiModelProperty(value = "地址")
	private String address;

	@ApiModelProperty(value = "创建日期")
	private Date createDate;

	@ApiModelProperty(value = "页码")
	private String rownum;
}