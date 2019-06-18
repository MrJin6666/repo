package com.goboosoft.company.wastemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.company.wastemanagement.dao.WasteManageDao;
import com.goboosoft.company.wastemanagement.dto.WasteManageDTO;
import com.goboosoft.company.wastemanagement.entity.WasteManageEntity;
import com.goboosoft.company.wastemanagement.service.WasteManageService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Service
public class WasteManageServiceImpl extends CrudServiceImpl<WasteManageDao, WasteManageEntity, WasteManageDTO> implements WasteManageService {

    @Autowired
    WasteManageDao wasteManageDao;

    @Override
    public QueryWrapper<WasteManageEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<WasteManageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<WasteManageEntity> wasteShowList(Long companyId, Integer inboundOrOutbound) {
        List<WasteManageEntity> wasteManageEntityResult = wasteManageDao.wasteShowList(companyId, inboundOrOutbound);
        return wasteManageEntityResult;
    }

    @Override
    public WasteManageEntity wasteShowDetails(Long wasteId) {
        WasteManageEntity wasteManageEntityResult = wasteManageDao.wasteShowDetails(wasteId);
        return wasteManageEntityResult;
    }

    @Override
    public List<WasteManageEntity> storeManageSearch(Long companyId, String wasteName, String startTime, String endTime) {
        List<WasteManageEntity> searchResult =
                wasteManageDao.storeManageSearch(companyId, wasteName, startTime, endTime);
        return searchResult;
    }

    @Override
    public void updateOutboundWaste(String wasteType, String wasteName, Long companyId) {
        baseDao.updateOutboundWaste(wasteType, wasteName, companyId);
    }

    @Override
    public List<WasteManageEntity> selectOutboundWasteCount(String wasteType, String wasteName, Long companyId) {
        List<WasteManageEntity> wasteManageEntities =
                baseDao.selectOutboundWasteCount(wasteType, wasteName, companyId);
        return wasteManageEntities;
    }

    @Override
    public Integer verifyingByTypeAndName(String wasteType, String wasteName, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("wasteType", wasteType);
        map.put("wasteName", wasteName);
        map.put("companyId", companyId);
        return baseDao.verifyingByTypeAndName(map);
    }

    @Override
    public List<WasteManageDTO> getDetailsByType(String wasteType, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("wasteType", wasteType);
        map.put("companyId", companyId);
        return baseDao.getDetailsByType(map);
    }

    @Override
    @Transactional
    public List<WasteManageEntity> storeOut(List<WasteManageDTO> manageDTOLists, Long companyId) {
        List<WasteManageEntity> lists = new ArrayList<WasteManageEntity>();
        try {
            for (WasteManageDTO wasteManageDTO : manageDTOLists) {
                wasteManageDTO.setInboundOrOutbound(1);
                wasteManageDTO.setCompanyId(companyId);
                wasteManageDTO.setOutboundDate(new Date());
                WasteManageEntity wasteManageEntity = ConvertUtils.sourceToTarget(wasteManageDTO, WasteManageEntity.class);
                baseDao.insert(wasteManageEntity);
                lists.add(wasteManageEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }


    @Override
    public WasteManageDTO updateCount(Integer count,Long companyId,String wasteType, String wasteName, int inboundOrOutbound){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("companyId",companyId);
        map.put("wasteType",wasteType);
        map.put("wasteName",wasteName);
        map.put("inboundOrOutbound",inboundOrOutbound);
        return baseDao.updateCount(map);

    }

    @Override
    public List<WasteManageEntity> selectByCompanyId(QueryWrapper wrapper){
        return baseDao.selectList(wrapper);
    }

    @Override
    public Integer isPassByCount(int count, String wasteName, String wasteType, Long companyId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("count",count);
        map.put("wasteName",wasteName);
        map.put("wasteType",wasteType);
        map.put("companyId",companyId);
        return baseDao.isPassByCount(map);
    }
}