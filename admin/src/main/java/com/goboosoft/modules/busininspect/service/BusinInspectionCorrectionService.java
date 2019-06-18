package com.goboosoft.modules.busininspect.service;


import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.modules.busininspect.entity.BusinInspectionCorrectionEntity;

import java.util.Map;

/**
 * 业务 - 整改基本信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
public interface BusinInspectionCorrectionService extends CrudService<BusinInspectionCorrectionEntity, BusinInspectionCorrectionDTO> {

    PageData<Map<String, Object>> companyScreenRe(Map<String, Object> params);
}