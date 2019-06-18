package com.goboosoft.company.wastemanagement.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.wastemanagement.dto.WasteTypeDTO;
import com.goboosoft.company.wastemanagement.entity.WasteTypeEntity;

/**
 * 企业危废管理内容类型
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
public interface WasteTypeService extends CrudService<WasteTypeEntity, WasteTypeDTO> {

    WasteTypeEntity  wasteContentTypeList(Long companyId);

    void inUpdateWasteType(WasteTypeDTO inWasteDTO);

    void outUpdateWasteType(WasteTypeDTO outWasteDTO);

}