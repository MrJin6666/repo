package com.goboosoft.company.govern.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 治理计划过程
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@ApiModel(value = "治理计划过程")
public class GovernProcessPlanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "治理计划id")
	private Long governPlanId;

	@ApiModelProperty(value = "图片")
	private String picture;

	@ApiModelProperty(value = "0 待处理 1 处理中 2 处理后 3 已完成 4 超期 5 延期 6治理前")
	private Integer process;

	@ApiModelProperty(value = "治理描述")
	private String describ;

	@ApiModelProperty(value = "管理员签字")
	private String sign;

	@ApiModelProperty(value = "抽查时间")
	private Date selectDate;

	@ApiModelProperty(value = "抽查时限")
	private Date selectLimitDate;

	@ApiModelProperty(value = "创建人")
	private String creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;


}