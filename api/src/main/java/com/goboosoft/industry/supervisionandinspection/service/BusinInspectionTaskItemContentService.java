package com.goboosoft.industry.supervisionandinspection.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.supervisionandinspection.dto.BusinInspectionTaskItemContentDTO;
import com.goboosoft.industry.supervisionandinspection.dto.InspectionContentDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemContentEntity;


/**
 * 业务 - 督导检查任务项内容信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
public interface BusinInspectionTaskItemContentService extends CrudService<BusinInspectionTaskItemContentEntity, BusinInspectionTaskItemContentDTO> {
    void saveInspectContent(InspectionContentDTO dto);
}