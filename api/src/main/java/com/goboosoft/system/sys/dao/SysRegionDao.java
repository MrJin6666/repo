package com.goboosoft.system.sys.dao;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.system.sys.dto.SysRegionDTO;
import com.goboosoft.system.sys.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Mapper
public interface SysRegionDao extends BaseDao<SysRegionEntity> {
    //省code码查询
    List<SysRegionDTO> getRegionProvince();
    //市code码查询
    List<SysRegionDTO> getRegionCity(@Param("adrProvince") String adrProvince);
    //区code码查询
    List<SysRegionDTO> getRegionArea(@Param("adrCity") String adrCity);
}