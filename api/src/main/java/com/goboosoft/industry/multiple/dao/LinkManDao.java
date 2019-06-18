package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.dto.QueryDeptAndChildDeptDTO;
import com.goboosoft.system.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jinxin
 */
@Mapper
@Repository
public interface LinkManDao extends BaseDao<SysUserEntity>{

    /**
     * 查询平级部门
     * @param pid
     * @return
     */
    List<QueryDeptAndChildDeptDTO> querySameLevelDept(Long pid);

    List<QueryDeptAndChildDeptDTO> querySysCompanyDept(@Param("params") Map<String, Object> params);
}
