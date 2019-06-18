package com.goboosoft.company.peoplemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@ApiModel(value = "企业 - 人员管理")
public class PeopleManagementDTO implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "人员姓名")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "人员姓名（权限显示用）")
    private String realName;

    @ApiModelProperty(value = "模块集合")
    private List<String> manuList;

    @ApiModelProperty(value = "是否是管理员")
    private Integer superAdmin;

    private String rownum;
}
