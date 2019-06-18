package com.goboosoft.company.wastemanagement.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.wastemanagement.dto.WasteSelectDTO;
import com.goboosoft.company.wastemanagement.entity.WasteSelectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 危废 选项
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-21
 */
@Mapper
public interface WasteSelectDao extends BaseDao<WasteSelectEntity> {

    List<WasteSelectDTO> getTypes(Long companyId);

    Long isExist(@Param("params") Map<String, Object> map);
}