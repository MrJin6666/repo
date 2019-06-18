package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.system.sys.entity.SysUserEntity;

/**
 * 查询Sys_user表
 * @author jinxin
 * created time 2019/3/7
 */
public interface QuerySysUserService extends BaseService<SysUserEntity> {
    /**
     * 根据手机号码查询Sys_user表，查看用户是否存在
     * @param mobile
     * @return
     */
    SysUserEntity queryById(String mobile);
}
