package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 企业基本信息
 */
@Data
@ApiModel(value = "企业基本信息")
public class BusinessDTO {
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "联系人")
    private String linkMan;
    @ApiModelProperty(value = "联系人电话")
    private String linkManPhone;
    @ApiModelProperty(value = "公司地址")
    private String address;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "企业检查状态")
    private String checkStatus;
    @ApiModelProperty(value = "企业审核状态")
    private String status;
    @ApiModelProperty(value = "页码")
    private String rownum;

}
