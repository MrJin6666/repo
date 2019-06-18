package com.goboosoft.company.govern.dto;

import com.goboosoft.company.govern.entity.GovernProcessPlanEntity;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


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

	@ApiModelProperty(value = "治理状态(0 待处理)")
	private String status;

	@ApiModelProperty(value = "计划状态 1 抽查 2 治理")
	private String planStatus;

	@ApiModelProperty(value = "治理项id")
	private Long inspectionListId;

	@ApiModelProperty(value = "治理项名称")
	private String governItemName;

	@ApiModelProperty(value = "企业id")
	private Long companyId;

	@ApiModelProperty(value = "治理项数量")
	private Integer countNum;

	@ApiModelProperty(value = "创建人部门id")
	private Long creatorDeptId;

	private BasicInspectionListDTO basicInspectionListDTO;

	private GovernProcessPlanDTO governProcessPlanDTO;

	private List<GovernProcessPlanEntity> list;

	private Long pid;

	private String pname;

	private String pictureBefore;

	private String pictureProcess;

	private String pictureAfter;

	@ApiModelProperty(value = "治理前-治理人")
	private String personBefore;

	@ApiModelProperty(value = "治理中-治理人")
	private String personProcess;

	@ApiModelProperty(value = "治理后-治理人")
	private String personAfter;

	@ApiModelProperty(value = "治理完成-签字人")
	private String personFinish;

	@ApiModelProperty(value = "治理前-时间")
	private Date dateBefore;

	@ApiModelProperty(value = "治理中-时间")
	private Date dateProcess;

	@ApiModelProperty(value = "治理后-时间")
	private Date dateAfter;

	@ApiModelProperty(value = "治理完成-时间")
	private Date dateFinish;

	@ApiModelProperty(value = "治理中描述")
	private String describProcess;

	@ApiModelProperty(value = "治理后描述")
	private String describAfter;

	private Date finishDate;

	private Long companyInspectionId;
	@ApiModelProperty("公司名称")
	private String companyName;

	private String oldStstus;

	private String rownum;

	private String checkname;

	private String sign;
}