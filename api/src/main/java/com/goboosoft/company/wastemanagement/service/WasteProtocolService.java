package com.goboosoft.company.wastemanagement.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.wastemanagement.dto.WasteProtocolDTO;
import com.goboosoft.company.wastemanagement.entity.WasteProtocolEntity;

import java.util.Date;
import java.util.List;

/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
public interface WasteProtocolService extends CrudService<WasteProtocolEntity, WasteProtocolDTO> {

    List<WasteProtocolDTO> protocolShowList(Long companyId);

    String wasteProtocolLastTime(Long companyId);
}