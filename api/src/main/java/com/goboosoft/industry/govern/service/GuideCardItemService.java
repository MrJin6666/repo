package com.goboosoft.industry.govern.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GuideCardItemDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemEntity;

import java.util.List;
import java.util.Map;

public interface GuideCardItemService extends BaseService<BusinInspectionTaskItemEntity> {
    List<GuideCardItemDTO> queryGuideCardItemBycheckStatus(Map<String,Object> params);
}
