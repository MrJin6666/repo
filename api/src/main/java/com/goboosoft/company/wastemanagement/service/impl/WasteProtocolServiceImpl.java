package com.goboosoft.company.wastemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.company.wastemanagement.dao.WasteProtocolDao;
import com.goboosoft.company.wastemanagement.dto.WasteProtocolDTO;
import com.goboosoft.company.wastemanagement.entity.WasteProtocolEntity;
import com.goboosoft.company.wastemanagement.service.WasteProtocolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业危废管理协议
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Service
public class WasteProtocolServiceImpl extends CrudServiceImpl<WasteProtocolDao, WasteProtocolEntity, WasteProtocolDTO> implements WasteProtocolService {

    @Override
    public QueryWrapper<WasteProtocolEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<WasteProtocolEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<WasteProtocolDTO> protocolShowList(Long companyId) {
        List<WasteProtocolDTO> dtos = baseDao.protocolShowList(companyId);
        return dtos;
    }

    @Override
    public String wasteProtocolLastTime(Long companyId) {
        String date = DateUtils.format(baseDao.wasteProtocolLastTime(companyId));
        return date;
    }
}