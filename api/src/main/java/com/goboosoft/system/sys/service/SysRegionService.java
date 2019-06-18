package com.goboosoft.system.sys.service;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.system.sys.dto.SysRegionDTO;
import com.goboosoft.system.sys.entity.SysRegionEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
public interface SysRegionService extends CrudService<SysRegionEntity, SysRegionDTO> {
    //省市区code码查询
   List<SysRegionDTO> getRegion(String adrProvince, String adrCity);
}