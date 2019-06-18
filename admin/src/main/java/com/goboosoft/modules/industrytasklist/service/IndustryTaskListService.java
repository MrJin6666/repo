package com.goboosoft.modules.industrytasklist.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskListDTO;
import com.goboosoft.modules.industrytasklist.entity.IndustryTaskListEntity;

import java.util.Map;

/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
public interface IndustryTaskListService extends CrudService<IndustryTaskListEntity, IndustryTaskListDTO> {

    PageData<IndustryTaskListDTO> pageList(Map<String, Object> params);

    void industry(IndustryTaskListDTO dto);

    void saveDTO(IndustryTaskListDTO dto);
}