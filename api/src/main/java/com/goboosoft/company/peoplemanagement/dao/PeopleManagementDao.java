package com.goboosoft.company.peoplemanagement.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.peoplemanagement.dto.PeopleManagementDTO;
import com.goboosoft.company.peoplemanagement.entity.PeopleManagementEntity;
import com.goboosoft.system.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * created by yangkun
 * created time 2019/2/11
 * class describe
 */
@Mapper
@Repository("peopleManageDao")
public interface PeopleManagementDao extends BaseDao<PeopleManagementEntity> {


    List<PeopleManagementDTO> peopleList(@Param("params") Map<String, Object> params);

    List<SysDictEntity> authorityManageList(String dictType);

    //Integer peopleAdd(String username, String mobile);
}
