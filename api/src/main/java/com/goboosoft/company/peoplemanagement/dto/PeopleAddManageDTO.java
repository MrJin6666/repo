package com.goboosoft.company.peoplemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("人员管理--添加")
public class PeopleAddManageDTO {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String userName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 权限集合
     */
    @ApiModelProperty(value = "模块集合")
    private List<String> manuList;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

}
