package com.goboosoft.company.wastemanagement.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.wastemanagement.dto.WasteManageDTO;
import com.goboosoft.company.wastemanagement.entity.WasteManageEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Mapper
@Repository("WasteManageDao")
public interface WasteManageDao extends BaseDao<WasteManageEntity> {

    List<WasteManageEntity> wasteShowList(@Param("companyId") Long companyId,
                                                 @Param("inboundOrOutbound") Integer inboundOrOutbound);

    WasteManageEntity wasteShowDetails(@Param("id") Long id);

    List<WasteManageEntity> storeManageSearch(@Param("companyId") Long companyId, @Param("wasteName") String wasteName,
                                              @Param("startTime") String startTime, @Param("endTime") String endTime);

    void updateOutboundWaste(@Param("wasteType") String wasteType,@Param("wasteName")
            String wasteName,@Param("companyId") Long companyId);

    List<WasteManageEntity> selectOutboundWasteCount(@Param("wasteType") String wasteType,@Param("wasteName")
            String wasteName,@Param("companyId") Long companyId);

    Integer verifyingByTypeAndName(@Param("params") Map<String, Object> map);

    List<WasteManageDTO> getDetailsByType(@Param("params")Map<String, Object> map);

    WasteManageDTO updateCount(@Param("params") Map<String, Object> map);

    Integer isPassByCount(@Param("params")Map<String, Object> map);
}