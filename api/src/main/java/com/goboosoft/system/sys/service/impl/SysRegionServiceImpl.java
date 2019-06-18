package com.goboosoft.system.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.system.sys.dao.SysRegionDao;
import com.goboosoft.system.sys.dto.SysRegionDTO;
import com.goboosoft.system.sys.entity.SysRegionEntity;
import com.goboosoft.system.sys.service.SysRegionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Service
public class SysRegionServiceImpl extends CrudServiceImpl<SysRegionDao, SysRegionEntity, SysRegionDTO> implements SysRegionService {
    @Autowired
    private SysRegionDao sysRegionDao;
    @Override
    public QueryWrapper<SysRegionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SysRegionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
    public List<SysRegionDTO> getRegion(String adrProvince, String adrCity){
        List<SysRegionDTO> srl= new ArrayList<>();
        if((adrProvince==null && adrCity==null) ||(adrProvince.equals("null") && adrCity.equals("null"))){
            srl = sysRegionDao.getRegionProvince();
        }else if((adrProvince!=null && adrCity==null)|| (!adrProvince.equals("null") && adrCity.equals("null"))){
            srl = sysRegionDao.getRegionCity(adrProvince);
        }else if (adrProvince!=null && adrCity!=null){
            srl = sysRegionDao.getRegionArea(adrCity);
        }
        return  srl;
    }


}