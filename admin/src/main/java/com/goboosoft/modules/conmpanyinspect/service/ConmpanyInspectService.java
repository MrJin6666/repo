package com.goboosoft.modules.conmpanyinspect.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.conmpanyinspect.dto.ConmpanyInspectDTO;
import com.goboosoft.modules.conmpanyinspect.entity.ConmpanyInspectEntity;

import java.util.List;
import java.util.Map;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
public interface ConmpanyInspectService extends CrudService<ConmpanyInspectEntity, ConmpanyInspectDTO> {

    List<ConmpanyInspectDTO> findConmpanyInspectBycomId(Long id);

    PageData<Map<String, Object>> screenCompanyInspectionNum(Map<String, Object> params);
}