package com.goboosoft.industry.multiple.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门详情类
 * @author jinxin
 * created time 2019/3/6
 */
@Data
@ApiModel(value = "联系人-部门详情信息")
public class SysDeptItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 人员id
     */
    private Long id;
    /**
     * 联系人姓名
     */
    private String realName;
    /**
     * 联系人电话
     */
    private String mobile;
    /**
     * 权限（超级管理员 0：否 1：是）
     */
    private Integer superAdmin;
    /**
     * 0 部门负责人 1 普通员工）
     */
    private Integer isDeptHead;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;
}
