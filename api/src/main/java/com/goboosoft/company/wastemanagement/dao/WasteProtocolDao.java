package com.goboosoft.company.wastemanagement.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.wastemanagement.dto.WasteProtocolDTO;
import com.goboosoft.company.wastemanagement.entity.WasteProtocolEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Mapper
public interface WasteProtocolDao extends BaseDao<WasteProtocolEntity> {

  List<WasteProtocolDTO> protocolShowList(Long companyId);

  Date wasteProtocolLastTime(Long companyId);
	
}