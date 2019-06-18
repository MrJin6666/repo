package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.multiple.dto.IndustryTaskLogDTO;
import com.goboosoft.industry.multiple.entity.IndustryTaskLogEntity;

/**
 * 行业任务日志记录
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
public interface IndustryTaskLogService extends CrudService<IndustryTaskLogEntity, IndustryTaskLogDTO> {
    /**
     * 任务进度记录
     */
   // void logSave(IndustryTaskLogEntity IndustryTaskLogEntity);
}