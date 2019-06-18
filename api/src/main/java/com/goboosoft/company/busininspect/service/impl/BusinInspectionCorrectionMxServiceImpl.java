package com.goboosoft.company.busininspect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.busininspect.dao.BusinInspectionCorrectionMxDao;
import com.goboosoft.company.busininspect.dto.BusinInspectionCorrectionMxDTO;
import com.goboosoft.company.busininspect.entity.BusinInspectionCorrectionMxEntity;
import com.goboosoft.company.busininspect.service.BusinInspectionCorrectionMxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 业务 - 整改明细信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-19
 */
@Service
public class BusinInspectionCorrectionMxServiceImpl extends CrudServiceImpl<BusinInspectionCorrectionMxDao, BusinInspectionCorrectionMxEntity, BusinInspectionCorrectionMxDTO> implements BusinInspectionCorrectionMxService {

    @Override
    public QueryWrapper<BusinInspectionCorrectionMxEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BusinInspectionCorrectionMxEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}