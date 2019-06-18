package com.goboosoft.industry.govern.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.govern.dto.GuideCardItemDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GuideCardItemDao extends BaseDao<BusinInspectionTaskItemEntity> {
    List<GuideCardItemDTO> queryGuideCardItemBycheckStatus(@Param("params") Map<String,Object> params);
}
