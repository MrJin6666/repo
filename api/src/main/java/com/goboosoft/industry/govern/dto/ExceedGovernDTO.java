package com.goboosoft.industry.govern.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "治理超期公司列表信息")
public class ExceedGovernDTO implements Comparable<ExceedGovernDTO>{

    @ApiModelProperty(value = "公司id")
    private Long companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "超期次数")
    private Integer exceedCount;

    @ApiModelProperty(value = "页码")
    private String rownum;


    @Override
    public int compareTo(ExceedGovernDTO o) {
        return o.getExceedCount()-this.getExceedCount();
    }
}
