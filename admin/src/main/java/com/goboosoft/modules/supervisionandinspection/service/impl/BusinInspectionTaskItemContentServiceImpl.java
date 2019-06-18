package com.goboosoft.modules.supervisionandinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.supervisionandinspection.dao.BusinInspectionTaskItemContentDao;
import com.goboosoft.modules.supervisionandinspection.dto.BusinInspectionTaskItemContentDTO;
import com.goboosoft.modules.supervisionandinspection.entity.BusinInspectionTaskItemContentEntity;
import com.goboosoft.modules.supervisionandinspection.service.BusinInspectionTaskItemContentService;
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

    @Override
    public QueryWrapper<BusinInspectionTaskItemContentEntity> getWrapper(Map<String, Object> params){
        Long inspectionTaskItemId = (Long)params.get("inspectionTaskItemId");

        QueryWrapper<BusinInspectionTaskItemContentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq( "inspection_task_item_id", inspectionTaskItemId);

        return wrapper;
    }

}