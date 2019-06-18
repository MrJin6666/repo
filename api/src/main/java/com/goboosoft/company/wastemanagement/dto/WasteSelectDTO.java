package com.goboosoft.company.wastemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 危废 选项
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-21
 */
@Data
@ApiModel(value = "危废 选项")
public class WasteSelectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "1.危废类型 2.危废名称 3.计量单位 4.废物来源 5.危废保存位置 6.接收单位 7.危废去向 8.所属部门")
	private Integer type;

	@ApiModelProperty(value = "企业id")
	private Long companyId;

	@ApiModelProperty(value = "")
	private Long creator;

	@ApiModelProperty(value = "")
	private Date createDate;

	private String name;
}