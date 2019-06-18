package com.goboosoft.industry.multiple.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.multiple.dao.LinkManDao;
import com.goboosoft.industry.multiple.dao.QuerySysDeptDao;
import com.goboosoft.industry.multiple.dto.QueryDeptAndChildDeptDTO;
import com.goboosoft.industry.multiple.service.LinkManService;
import com.goboosoft.system.security.user.SecurityUser;
import com.goboosoft.system.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 联系人
 * @author jinxin
 */
@Service
public class LinkManServiceImpl extends BaseServiceImpl<LinkManDao,SysUserEntity> implements LinkManService {
    @Autowired
    private LinkManDao linkManDao;

    @Autowired
    private QuerySysDeptDao querySysDeptDao;
    @Override
    public List<QueryDeptAndChildDeptDTO> queryDeptAndChildDept(Long deptId) {
        Long id = SecurityUser.getDeptId();
        Long pid = querySysDeptDao.selectById(id).getPid();
        if (pid == 0) {
            pid = id;
        }
        return query(pid);
    }

    public List<QueryDeptAndChildDeptDTO> query (long id) {
        List<QueryDeptAndChildDeptDTO> dtos = linkManDao.querySameLevelDept(id);
        for (QueryDeptAndChildDeptDTO dto : dtos) {
            id = dto.getDeptId();
            dto.setChild(query(id));
        }
        return dtos;
    }
    public List<QueryDeptAndChildDeptDTO> querySysCompanyDept(Map<String,Object> params){
       return linkManDao.querySysCompanyDept(params);
    }
}
