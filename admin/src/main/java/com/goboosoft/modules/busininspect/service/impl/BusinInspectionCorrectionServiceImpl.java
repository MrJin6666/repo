package com.goboosoft.modules.busininspect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.busininspect.dao.BusinInspectionCorrectionDao;
import com.goboosoft.modules.busininspect.dto.BusinInspectionCorrectionDTO;
import com.goboosoft.modules.busininspect.entity.BusinInspectionCorrectionEntity;
import com.goboosoft.modules.busininspect.service.BusinInspectionCorrectionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 业务 - 整改基本信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-15
 */
@Service
public class BusinInspectionCorrectionServiceImpl extends CrudServiceImpl<BusinInspectionCorrectionDao, BusinInspectionCorrectionEntity, BusinInspectionCorrectionDTO> implements BusinInspectionCorrectionService {

    @Override
    public QueryWrapper<BusinInspectionCorrectionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BusinInspectionCorrectionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<Map<String, Object>> companyScreenRe(Map<String, Object> params) {
        IPage<Map<String, Object>> pageList = baseDao.companyScreenRe(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<Map<String, Object>> objectPageData = new PageData<Map<String, Object>>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }
}