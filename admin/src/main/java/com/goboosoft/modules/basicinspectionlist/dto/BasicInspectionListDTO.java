package com.goboosoft.modules.basicinspectionlist.dto;

import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Data
@ApiModel(value = "基础 - 检查项/检查内容信息表")
public class BasicInspectionListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "检查项/检查内容名称")
	private String name;

	@ApiModelProperty(value = "级别 0检查项 1检查内容")
	private String level;

	@ApiModelProperty(value = "上级主键")
	private Long pid;

	@ApiModelProperty(value = "总分")
	private Integer totalScore;

	@ApiModelProperty(value = "周期")
	private long cycle;

	@ApiModelProperty(value = "详情")
	private String details;

	@ApiModelProperty(value = "内容类型")
	private String contentType;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "子类")
	private List<BasicInspectionListDTO> children;

	@ApiModelProperty(value = "治理步骤")
	private List<BasicInspectionStepsDTO> stepsList;

	@ApiModelProperty(value = "数量")
	private long num;

	@ApiModelProperty(value = "提前预警天数")
	private long aheadDay;

	@ApiModelProperty(value = "用户检查分类id")
	private long companyInspectId;
}