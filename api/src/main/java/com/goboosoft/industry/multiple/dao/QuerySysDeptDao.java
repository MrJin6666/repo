package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.dto.QueryDeptAndChildDeptDTO;
import com.goboosoft.system.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询Sys_dept表
 * @author jinxin
 * created time 2019/3/7
 */
@Mapper
@Repository
public interface QuerySysDeptDao extends BaseDao<SysDeptEntity> {

}
