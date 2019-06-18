package com.goboosoft.modules.governplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Data
@ApiModel(value = "治理计划")
public class GovernPlanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "治理人")
	private String person;

	@ApiModelProperty(value = "单号")
	private String code;

	@ApiModelProperty(value = "治理时间")
	private Date seatDate;

	@ApiModelProperty(value = "创建人")
	private String creator;

	@ApiModelProperty(value = "创建时间(计划开始时间)")
	private Date createDate;

	@ApiModelProperty(value = "计划结束时间")
	private Date endDate;

	@ApiModelProperty(value = "项目名称")
	private String name;

	@ApiModelProperty(value = "治理状态")
	private String status;

	@ApiModelProperty(value = "计划状态 1 抽查 2 治理")
	private String planStatus;

	@ApiModelProperty(value = "治理项id")
	private long inspectionListId;

	@ApiModelProperty(value = "公司id")
	private long companyId;

	@ApiModelProperty(value = "用户治理项id")
	private long companyInspectionId;

}