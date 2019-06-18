package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "督查结果")
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "督查主键")
	private Long id;

	@ApiModelProperty(value = "总检查项")
	private Integer inspectionCount;

	@ApiModelProperty(value = "不合格总数")
	private Integer noQualifiedCount;

	@ApiModelProperty(value = "检查项目")
	private List<ResultItemListDTO> ResultItemListDTO;

}