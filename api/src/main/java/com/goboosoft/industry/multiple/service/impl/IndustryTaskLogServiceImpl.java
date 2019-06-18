package com.goboosoft.industry.multiple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.industry.multiple.dao.IndustryTaskLogDao;
import com.goboosoft.industry.multiple.dto.IndustryTaskLogDTO;
import com.goboosoft.industry.multiple.entity.IndustryTaskLogEntity;
import com.goboosoft.industry.multiple.service.IndustryTaskLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 行业任务日志记录
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Service
public class IndustryTaskLogServiceImpl extends CrudServiceImpl<IndustryTaskLogDao, IndustryTaskLogEntity, IndustryTaskLogDTO> implements IndustryTaskLogService {

    @Override
    public QueryWrapper<IndustryTaskLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String industryTaskId = (String)params.get("industryTaskId");

        QueryWrapper<IndustryTaskLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(industryTaskId), "industry_task_id", industryTaskId);

        return wrapper;
    }
}