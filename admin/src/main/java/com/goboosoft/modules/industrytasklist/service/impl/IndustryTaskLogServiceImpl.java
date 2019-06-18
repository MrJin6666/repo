package com.goboosoft.modules.industrytasklist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.industrytasklist.dao.IndustryTaskLogDao;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskLogDTO;
import com.goboosoft.modules.industrytasklist.entity.IndustryTaskLogEntity;
import com.goboosoft.modules.industrytasklist.service.IndustryTaskLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 行业任务日志记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Service
public class IndustryTaskLogServiceImpl extends CrudServiceImpl<IndustryTaskLogDao, IndustryTaskLogEntity, IndustryTaskLogDTO> implements IndustryTaskLogService {

    @Override
    public QueryWrapper<IndustryTaskLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<IndustryTaskLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}