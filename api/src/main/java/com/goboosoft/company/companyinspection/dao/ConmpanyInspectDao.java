package com.goboosoft.company.companyinspection.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.entity.ConmpanyInspectEntity;
import com.goboosoft.industry.homepage.dto.InspectDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Mapper
public interface ConmpanyInspectDao extends BaseDao<ConmpanyInspectEntity> {

    List<ConmpanyInspectDTO> getListNameAndNum(Long userId);

    Integer updateCompanyId(@Param("companyId") Long companyId,@Param("userId") Long userId);

    Integer getCount(@Param("listId") Long conmpanyInspectId,@Param("companyId") Long companyId,@Param("userId") Long userId);

    /**
     * 根据企业id和检查项id查询检查项列表
     */
    List<ConmpanyInspectDTO> getInspectCountById(@Param("companyId") Long companyId ,@Param("listId") Long listId);
    /**
     * 检查项分组总数统计
     */
    List<ConmpanyInspectDTO> getInspectCountByGroup(@Param("userId") Long userId);
    /**
     * 根据检查项id获取检查项统计详情列表
     */
    List<InspectDetailsDTO> getInspectCountDetails(@Param("params") Map<String,Object> params);

    Integer getByName(@Param("name") String name);

    Integer isExits(@Param("dto") ConmpanyInspectDTO dto);
}