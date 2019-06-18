package com.goboosoft.company.wastemanagement.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.wastemanagement.dto.WasteSelectDTO;
import com.goboosoft.company.wastemanagement.dto.WasteTypeListsDTO;
import com.goboosoft.company.wastemanagement.entity.WasteSelectEntity;

import java.util.List;

/**
 * 危废 选项
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-21
 */
public interface WasteSelectService extends CrudService<WasteSelectEntity, WasteSelectDTO> {

    WasteTypeListsDTO getTypes(Long companyId);

    Long isExist(String wasteType, Long companyId, Integer type);

    Boolean saveType(String name,Long companyId,Integer type);
}