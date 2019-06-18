package com.goboosoft.company.wastemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.wastemanagement.dao.WasteTypeDao;
import com.goboosoft.company.wastemanagement.dto.WasteTypeDTO;
import com.goboosoft.company.wastemanagement.entity.WasteTypeEntity;
import com.goboosoft.company.wastemanagement.service.WasteTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 企业危废管理内容类型
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Service
public class WasteTypeServiceImpl extends CrudServiceImpl<WasteTypeDao, WasteTypeEntity, WasteTypeDTO> implements WasteTypeService {

    @Override
    public QueryWrapper<WasteTypeEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WasteTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public WasteTypeEntity wasteContentTypeList(Long companyId) {
        WasteTypeEntity typeEntity = baseDao.wasteContentTypeList(companyId);
        return typeEntity;
    }

    @Override
    public void inUpdateWasteType(WasteTypeDTO inWasteDTO) {
        baseDao.inUpdateWasteType(inWasteDTO);
    }

    @Override
    public void outUpdateWasteType(WasteTypeDTO outWasteDTO) {
        baseDao.outUpdateWasteType(outWasteDTO);
    }
}