package com.goboosoft.industry.company.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公司信息
 *
 * @author jinxin
 */
@Data
@ApiModel(value = "公司信息")
public class QueryCompanyListDTO {
    @ApiModelProperty(value = "公司id")
    private String companyId;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "最后提交时间")
    private String seatDate;
    @ApiModelProperty(value = "页码")
    private String rownum;
}
