package com.goboosoft.industry.supervisionandinspection.service;


import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.supervisionandinspection.dto.*;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import com.goboosoft.system.sys.dto.SysFileDTO;

import java.util.List;
import java.util.Map;

/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
public interface BusinInspectionTaskService extends CrudService<BusinInspectionTaskEntity, BusinInspectionTaskDTO> {

    Long saveBusinInspectionTask(BusinInspectionTaskDTO dto);

    List<BusinInspectionTaskListDTO> getList(Map<String, Object> params);

    List<CorrectionListDTO> getCorrectionList(Map<String, Object> params);

    List<CorrectionInfoDTO> getCorrectionContent(Long id, Integer type);

    CorrectionDTO getCorrectionContentInfo(Long id);

    List<CheckListDTO> getCheckList(Map<String, Object> params);

    CheckDTO getCheckContentInfo(Long id);

    List<ReportDTO> getReportList(Map<String, Object> params);

    List<ReportDTO> getReportListByCompanyId(Map<String, Object> params);

    ReportInfoDTO getReportInfo(Long id);

    Integer getInspectionCountByCompanyId(Long companyId);

    Integer getCheckCountByUserId();

    CompanyGuideDTO getCompanyGuide(Long companyId);

    String getIdByFiles(List<SysFileDTO> fileDTOS);

    Integer getCorrectionCountByUserId();

    String addPhotoPath(String photos);

    PageData<BusinInspectionTaskItemContentDTO> getInspectContent(Map<String, Object> params);

    InspectionTaskInfoDTO getInspectionTaskInfo(Long id);

    PageData<BusinInspectionTaskItemListDTO> getCompanyinspect(Map<String, Object> params);

    PageData<InspectionTaskItemContentListDTO> getCompanyInspectContent(Map<String, Object> params);

    void saveInspectContent(List<InspectionContentDTO> dtos);

    ResultDTO getInspectResult(Map<String, Object> params);

    void saveBusinInspectionReport(InspectionTaskReportDTO dto);

    void saveCheckContent(CheckSaveDTO dto);

    ResultDTO getReportResult(Map<String, Object> params);
}
