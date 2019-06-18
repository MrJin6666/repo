package com.goboosoft.company.wastemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.wastemanagement.dao.WasteSelectDao;
import com.goboosoft.company.wastemanagement.dto.WasteSelectDTO;
import com.goboosoft.company.wastemanagement.dto.WasteTypeListsDTO;
import com.goboosoft.company.wastemanagement.entity.WasteSelectEntity;
import com.goboosoft.company.wastemanagement.service.WasteSelectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 危废 选项
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-21
 */
@Service
public class WasteSelectServiceImpl extends CrudServiceImpl<WasteSelectDao, WasteSelectEntity, WasteSelectDTO> implements WasteSelectService {

    @Override
    public QueryWrapper<WasteSelectEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WasteSelectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public WasteTypeListsDTO getTypes(Long companyId){
        List<WasteSelectDTO> lists = baseDao.getTypes(companyId);
        WasteTypeListsDTO wasteTypeListsDTO = new WasteTypeListsDTO();
        List<WasteSelectDTO> wasteSelectTypes =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectNames =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectUnits =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectSources =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectLocals =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectRevices =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectGones =  new ArrayList<>();
        List<WasteSelectDTO> wasteSelectDepts =  new ArrayList<>();
        for(WasteSelectDTO wasteSelectDTO : lists){

            if(wasteSelectDTO.getType() == 1){
                wasteSelectTypes.add(wasteSelectDTO);
            }else if(wasteSelectDTO.getType() == 2){
                wasteSelectNames.add(wasteSelectDTO);
            }else if(wasteSelectDTO.getType() == 3){
                wasteSelectUnits.add(wasteSelectDTO);
            }else if(wasteSelectDTO.getType() == 4){
                wasteSelectSources.add(wasteSelectDTO);
            }else if(wasteSelectDTO.getType() == 5){
                wasteSelectLocals.add(wasteSelectDTO);
            }else if(wasteSelectDTO.getType() == 6){
                wasteSelectRevices.add(wasteSelectDTO);
            }else if(wasteSelectDTO.getType() == 7){
                wasteSelectGones.add(wasteSelectDTO);
            }else{
                wasteSelectDepts.add(wasteSelectDTO);
            }
        }
        wasteTypeListsDTO.setWasteSelectTypes(wasteSelectTypes);
        wasteTypeListsDTO.setWasteSelectNames(wasteSelectNames);
        wasteTypeListsDTO.setWasteSelectUnits(wasteSelectUnits);
        wasteTypeListsDTO.setWasteSelectSources(wasteSelectSources);
        wasteTypeListsDTO.setWasteSelectLocals(wasteSelectLocals);
        wasteTypeListsDTO.setWasteSelectRevices(wasteSelectRevices);
        wasteTypeListsDTO.setWasteSelectGones(wasteSelectGones);
        wasteTypeListsDTO.setWasteSelectDepts(wasteSelectDepts);
        return  wasteTypeListsDTO;
    }

    @Override
    public Long isExist(String wasteType, Long companyId,Integer type){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("wasteType",wasteType);
        map.put("companyId",companyId);
        map.put("type",type);
        return baseDao.isExist(map);
    }

    @Override
    @Transactional
    public Boolean saveType(String name,Long companyId,Integer type){

        Boolean result = false;
        Long integer = isExist(name, companyId, type);
        if(integer == null ){
            WasteSelectEntity wasteSelectEntity = new WasteSelectEntity();
            wasteSelectEntity.setCompanyId(companyId);
            wasteSelectEntity.setName(name);
            wasteSelectEntity.setType(type);
            baseDao.insert(wasteSelectEntity);
        }else{
            result = true;
        }
        return  result;
    }
}