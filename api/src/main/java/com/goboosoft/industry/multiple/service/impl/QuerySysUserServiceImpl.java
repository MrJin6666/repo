package com.goboosoft.industry.multiple.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.multiple.dao.QuerySysUserDao;
import com.goboosoft.industry.multiple.service.QuerySysUserService;
import com.goboosoft.system.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询Sys_user表
 * @author jinxin
 * created time 2019/3/7
 */
@Service
public class QuerySysUserServiceImpl extends BaseServiceImpl<QuerySysUserDao,SysUserEntity> implements QuerySysUserService {
    @Autowired
    private QuerySysUserDao querySysUserDao;

    /**
     * 根据手机号码查询Sys_user表，查看用户是否存在(查不到返回null)
     * @param mobile
     * @return
     */
    @Override
    public SysUserEntity queryById(String mobile) {

        try {
            SysUserEntity sysUserEntity = querySysUserDao.queryById(mobile);
            return sysUserEntity;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
