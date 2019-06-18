package com.goboosoft.company.peoplemanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 人员管理实体类
 *  * @author yangfl
 *  * @since 1.0.0 2019-03.6
 */
@Data
@TableName("sys_user")
public class PeopleManagementEntity implements Serializable {

    /**
     *id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     *人员姓名
     */
    private String realName;

    /**
     *用户头像
     */
    private String headUrl;

    /**
     * 性别 0：男  1：女   3：保密
     */
    private String gender;
    /**
     *邮箱
     */
    private String email;

    /**
     *手机号码
     */
    private String mobile;

    /**
     * 部门
     */
    private String deptId;

    /**
     *用户权限 0：否 1：是
     */
    private Integer superAdmin;

    /**
     *公司主键
     */
    private String companyId;

    /**
     *状态 0：停用 1：正常
     */
    private String status;

    /**
     *创建者
     */
    private Long creator;

    /**
     *创建时间
     */
    private Date createDate;

    /**
     *更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateDate;

}
