package com.goboosoft.company.manageread.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.manageread.dto.ManageReadDTO;
import com.goboosoft.company.manageread.entity.ManageReadEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 公告记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-20
 */
@Mapper
public interface ManageReadDao extends BaseDao<ManageReadEntity> {

    List<ManageReadDTO> getLists(@Param("params") Map<String, Object> map);

    List<ManageReadDTO> selectLists(@Param("id") Long id, @Param("manageId") Long manageId);

    Integer deleteManageRead(@Param("params") Map<String, Object> map);
}