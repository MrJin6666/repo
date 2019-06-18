package com.goboosoft.industry.supervisionandinspection.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Data
@ApiModel(value = "整改项/检查项 以及其内容")
public class CorrectionInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "整改检查项名称")
	private String inspectionTaskItemName;

	@ApiModelProperty(value = "整改项")
	private List<CorrectionContent> correctionContentList;

}