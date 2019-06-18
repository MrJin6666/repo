package com.goboosoft.industry.homepage.dto;

import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "企业总数统计")
public class EnterprisesTotalDTO {
    @ApiModelProperty(value = "企业总数")
    private Long companyNum;
    @ApiModelProperty(value = "检查项列表")
    private List<ConmpanyInspectDTO> InspectList;
}
