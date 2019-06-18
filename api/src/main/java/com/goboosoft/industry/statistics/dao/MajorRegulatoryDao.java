package com.goboosoft.industry.statistics.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.statistics.dto.MajorRegulatoryCompanyListDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 统计-重点监管
 *
 * @author jinxin
 */
@Mapper
@Repository
public interface MajorRegulatoryDao extends BaseDao<BusinInspectionTaskEntity> {
    Integer queryCompanyList(@Param("params") Map<String, Object> params);

    List<MajorRegulatoryCompanyListDTO> queryMajorRegulatoryCompanyList(@Param("params") Map<String,Object> params);
}
