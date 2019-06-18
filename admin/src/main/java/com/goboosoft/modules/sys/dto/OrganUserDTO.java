package com.goboosoft.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月07日 13:13
 * version 1.0
 */
@Data
@ApiModel(value = "组织架构下的人员列表")
public class OrganUserDTO {

    private long id;

    private String userName;

    private String deptName;

    private String isChild;

    private String realName;

    private int status;

    private int isDeptHead;


}
