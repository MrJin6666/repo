package com.goboosoft.modules.supervisionandinspection.service;


import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.supervisionandinspection.dto.BusinInspectionTaskDTO;
import com.goboosoft.modules.supervisionandinspection.entity.BusinInspectionTaskEntity;

import java.util.Map;

/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
public interface BusinInspectionTaskService extends CrudService<BusinInspectionTaskEntity, BusinInspectionTaskDTO> {
    PageData<Map<String, Object>> screenDd(Map<String, Object> params);
}