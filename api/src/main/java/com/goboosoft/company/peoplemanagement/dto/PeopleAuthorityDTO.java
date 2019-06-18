package com.goboosoft.company.peoplemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
@ApiModel(value = "企业 - 权限管理")
public class PeopleAuthorityDTO {

    @ApiModelProperty(value = "权限id")
    private Long id;

    @ApiModelProperty(value = "权限类型")
    private String dictType;

    @ApiModelProperty(value = "权限名")
    private String dictName;

    @ApiModelProperty(value = "权限值")
    private String dictValue;



//    @ApiModelProperty(value = "是否选中（0：选中  1：未选中）")
//    private Integer authorityStatus;


}
