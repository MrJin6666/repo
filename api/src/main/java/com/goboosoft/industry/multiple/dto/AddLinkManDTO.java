package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加联系人
 * @author jinxin
 * created time 2019/3/7
 */
@Data
@ApiModel(value = "添加联系人")
public class AddLinkManDTO implements Serializable {
    /**
     * 姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 部门
     */
    private Long deptId;
    /**
     * 权限
     */
    private Integer isDeptHead;

}
