package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.multiple.dto.QueryDeptAndChildDeptDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author JINXIN
 */
public interface LinkManService extends BaseService<SysUserEntity> {
    List<QueryDeptAndChildDeptDTO> queryDeptAndChildDept(Long deptId);

    List<QueryDeptAndChildDeptDTO> querySysCompanyDept(Map<String,Object> params);
}
