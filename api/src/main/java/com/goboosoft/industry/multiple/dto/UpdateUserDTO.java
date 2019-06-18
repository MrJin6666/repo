package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改人员信息
 * @author jinxin
 *
 */
@Data
@ApiModel(value = "修改人员信息")
public class UpdateUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "部门id")
    private String deptId;

    @ApiModelProperty(value = "是否是部门领导")
    private String isDeptHead;
}
