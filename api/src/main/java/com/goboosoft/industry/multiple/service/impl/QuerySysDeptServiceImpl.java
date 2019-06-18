package com.goboosoft.industry.multiple.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.multiple.dao.QuerySysDeptDao;
import com.goboosoft.industry.multiple.dao.QuerySysUserDao;
import com.goboosoft.industry.multiple.service.QuerySysDeptService;
import com.goboosoft.industry.multiple.service.QuerySysUserService;
import com.goboosoft.system.sys.entity.SysDeptEntity;
import com.goboosoft.system.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询Sys_user表
 * @author jinxin
 * created time 2019/3/7
 */
@Service
public class QuerySysDeptServiceImpl extends BaseServiceImpl<QuerySysDeptDao,SysDeptEntity> implements QuerySysDeptService {

}
