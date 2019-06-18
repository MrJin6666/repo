package com.goboosoft.company.wastemanagement.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.wastemanagement.dto.WasteManageDTO;
import com.goboosoft.company.wastemanagement.entity.WasteManageEntity;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
public interface WasteManageService extends CrudService<WasteManageEntity, WasteManageDTO> {

    List<WasteManageEntity> wasteShowList(Long companyId, Integer inboundOrOutbound);

    WasteManageEntity wasteShowDetails(Long wasteId);

    List<WasteManageEntity> storeManageSearch(Long companyId, String wasteName, String startTime, String endTime);

    void updateOutboundWaste(String wasteType, String wasteName, Long companyId);

    List<WasteManageEntity> selectOutboundWasteCount(String wasteType, String wasteName, Long companyId);

    Integer verifyingByTypeAndName(String wasteType, String wasteName, Long companyId);

    List<WasteManageDTO> getDetailsByType(String wasteType, Long companyId);

    List<WasteManageEntity> storeOut(List<WasteManageDTO> manageDTOLists,Long companyId);

    WasteManageDTO updateCount(Integer count, Long companyId, String wasteType, String wasteName, int inboundOrOutbound);

    List<WasteManageEntity> selectByCompanyId(QueryWrapper wrapper);

    Integer isPassByCount(int i, String wasteName, String wasteType, Long companyId);
}