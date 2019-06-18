package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.system.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 查询Sys_user表
 * @author jinxin
 * created time 2019/3/7
 */
@Mapper
@Repository
public interface QuerySysUserDao extends BaseDao<SysUserEntity> {
    /**
     * 根据手机号码查询Sys_user表，查看用户是否存在
     * @param mobile
     * @return
     */
    SysUserEntity queryById(String mobile);
}
