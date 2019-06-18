package com.goboosoft.company.busininspect.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.company.busininspect.dto.InspectionCorrectionDetailsDTO;
import com.goboosoft.company.busininspect.dto.InspectionCorrectionListDTO;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionEntity;

import java.util.List;
import java.util.Map;

/**
 * 业务 - 整改基本信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
public interface BusinInspectionCorrectionService extends CrudService<BusinInspectionCorrectionEntity, BusinInspectionCorrectionDTO> {

    List<InspectionCorrectionListDTO> getInspectionCorrectionListByCompanyId(Map<String, Object> params);

    InspectionCorrectionDetailsDTO getInspectionCorrectionDetailsById(Long id);

    Long getChild(Long id);

    List<Integer> getStatus(Long id);

    /**
     * 获取督导整改任务数量
     * @param companyId 企业id
     * @return 数量
     */
    Integer getInspectionCorrectionCountByCompanyId(Long companyId);

}