package com.goboosoft.industry.multiple.dto;

import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 企业基本信息
 * @author jinxin
 */
@Data
@ApiModel(value = "企业详细信息")
public class BusinessItemDTO {

    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "企业状态")
    private Integer status;

    @ApiModelProperty(value = "企业是否停业状态")
    private Integer resultIsClosed;

    @ApiModelProperty(value = "所属管理部门")
    private String deptName;

    @ApiModelProperty(value = "监管负责人")
    private String managerName;

    @ApiModelProperty(value = "联系人")
    private String linkMan;

    @ApiModelProperty(value = "联系人电话")
    private String linkManPhone;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "工商执照发证机关")
    private String issuingAuthority;

    @ApiModelProperty(value = "营业执照图片")
    private String licence;

    @ApiModelProperty(value = "检查分类名称及数量")
    private List<ConmpanyInspectDTO> list;

}
