package com.goboosoft.company.busininspect.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.busininspect.dto.InspectionCorrectionDetailsDTO;
import com.goboosoft.company.busininspect.dto.InspectionCorrectionListDTO;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Mapper
public interface BusinInspectionCorrectionDao extends BaseDao<BusinInspectionCorrectionEntity> {

  List<InspectionCorrectionListDTO> getInspectionCorrectionListByCompanyId(Long companyId);

  InspectionCorrectionDetailsDTO getInspectionCorrectionDetailsById(Long id);

    Long getChild(@Param("id") Long id);

    List<Integer> getStatus(@Param("id") Long id);

  List<InspectionCorrectionListDTO> getInspectionCorrectionListByParams(@Param("params") Map<String, Object> params);
}