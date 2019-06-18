package com.goboosoft.modules.supervisionandinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.supervisionandinspection.dao.BusinInspectionTaskItemDao;
import com.goboosoft.modules.supervisionandinspection.dto.BusinInspectionTaskItemDTO;
import com.goboosoft.modules.supervisionandinspection.entity.BusinInspectionTaskItemEntity;
import com.goboosoft.modules.supervisionandinspection.service.BusinInspectionTaskItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 业务 - 督导检查任务项信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Service
public class BusinInspectionTaskItemServiceImpl extends CrudServiceImpl<BusinInspectionTaskItemDao, BusinInspectionTaskItemEntity, BusinInspectionTaskItemDTO> implements BusinInspectionTaskItemService {

    @Override
    public QueryWrapper<BusinInspectionTaskItemEntity> getWrapper(Map<String, Object> params){
        String source = (String)params.get("source");
        Long businInspectionTaskId = (Long)params.get("businInspectionTaskId");

        QueryWrapper<BusinInspectionTaskItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(source), "source", source);
        wrapper.eq( "inspection_task_id ", businInspectionTaskId);

        return wrapper;
    }


}