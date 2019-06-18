package com.goboosoft.industry.supervisionandinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.industry.supervisionandinspection.dao.BusinInspectionTaskItemDao;
import com.goboosoft.industry.supervisionandinspection.dto.BusinInspectionTaskItemDTO;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemEntity;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskItemService;
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
        String businInspectionTaskId = (String)params.get("businInspectionTaskId");

        QueryWrapper<BusinInspectionTaskItemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(businInspectionTaskId), "inspection_task_id ", businInspectionTaskId);

        return wrapper;
    }


}