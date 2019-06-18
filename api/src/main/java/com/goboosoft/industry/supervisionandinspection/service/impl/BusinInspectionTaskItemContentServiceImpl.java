package com.goboosoft.industry.supervisionandinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.industry.supervisionandinspection.dao.BusinInspectionTaskItemContentDao;
import com.goboosoft.industry.supervisionandinspection.dto.BusinInspectionTaskItemContentDTO;
import com.goboosoft.industry.supervisionandinspection.dto.InspectionContentDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemContentEntity;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemContentService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 业务 - 督导检查任务项内容信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Service
public class BusinInspectionTaskItemContentServiceImpl extends CrudServiceImpl<BusinInspectionTaskItemContentDao, BusinInspectionTaskItemContentEntity, BusinInspectionTaskItemContentDTO> implements BusinInspectionTaskItemContentService {

    @Autowired
    private BusinInspectionTaskService businInspectionTaskService;
    @Override
    public QueryWrapper<BusinInspectionTaskItemContentEntity> getWrapper(Map<String, Object> params) {
        String inspectionTaskItemId = (String) params.get("inspectionTaskItemId");

        QueryWrapper<BusinInspectionTaskItemContentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(inspectionTaskItemId), "inspection_task_item_id", inspectionTaskItemId);

        return wrapper;
    }

    @Override
    public void saveInspectContent(InspectionContentDTO dto) {
        BusinInspectionTaskItemContentDTO businInspectionTaskItemContentDTO = this.get(dto.getId());
        businInspectionTaskItemContentDTO.setPassStatus(dto.getPassStatus());
        String idByFiles = businInspectionTaskService.getIdByFiles(dto.getFiles());
        businInspectionTaskItemContentDTO.setPhotos(idByFiles);
        businInspectionTaskItemContentDTO.setScore(dto.getScore());
        businInspectionTaskItemContentDTO.setRemark(dto.getRemark());
        this.update(businInspectionTaskItemContentDTO);
    }
}