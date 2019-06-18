package com.goboosoft.company.wastemanagement.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.wastemanagement.dto.WasteTypeDTO;
import com.goboosoft.company.wastemanagement.entity.WasteTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 企业危废管理内容类型
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Mapper
public interface WasteTypeDao extends BaseDao<WasteTypeEntity> {

  WasteTypeEntity  wasteContentTypeList(@Param("companyId") Long companyId);

  void inUpdateWasteType(WasteTypeDTO inWasteDTO);

  void outUpdateWasteType(WasteTypeDTO outWasteDTO);
	
}