package com.goboosoft.industry.supervisionandinspection.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.statistics.dto.SupervisePeopleDTO;
import com.goboosoft.industry.supervisionandinspection.dto.*;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Mapper
public interface BusinInspectionTaskDao extends BaseDao<BusinInspectionTaskEntity> {

    List<InspectionDTO> getInspectionListByCompanyId(@Param("companyId") Long companyId);

    List<InspectionDTO> getInspectionListByPid(@Param("pid") Long pid);

    List<BusinInspectionTaskListDTO> getList(@Param("params") Map<String, Object> params);

    List<CorrectionListDTO> getCorrectionList(@Param("params")Map<String, Object> params);

    List<CorrectionInfoDTO> getCorrectionContent(@Param("id")Long id,@Param("type")Integer type);

    CorrectionDTO getCorrectionContentInfo(@Param("id")Long id);

    List<CheckListDTO> getCheckList(@Param("params")Map<String, Object> params);

    CheckDTO getCheckContentInfo(@Param("id")Long id);

    List<ReportDTO> getReportList(@Param("params")Map<String, Object> params);

    List<ReportDTO> getReportListByCompanyId(@Param("params")Map<String, Object> params);

    ReportInfoDTO getReportInfo(@Param("id")Long id);

    Integer getInspectionCountByCompanyId(@Param("companyId") Long companyId);

    Integer getCheckCountByUserId(@Param("userId") Long userId);

    Integer getCorrectionCountByUserId(@Param("userId") Long userId);
    /**
     * 查询督导人次
     */
    List<SupervisePeopleDTO> getSupervisePeople(@Param("params") Map<String, Object> params);
}