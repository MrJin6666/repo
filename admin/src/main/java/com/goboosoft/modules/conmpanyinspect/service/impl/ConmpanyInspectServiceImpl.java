package com.goboosoft.modules.conmpanyinspect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.conmpanyinspect.dao.ConmpanyInspectDao;
import com.goboosoft.modules.conmpanyinspect.dto.ConmpanyInspectDTO;
import com.goboosoft.modules.conmpanyinspect.entity.ConmpanyInspectEntity;
import com.goboosoft.modules.conmpanyinspect.service.ConmpanyInspectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Service
public class ConmpanyInspectServiceImpl extends CrudServiceImpl<ConmpanyInspectDao, ConmpanyInspectEntity, ConmpanyInspectDTO> implements ConmpanyInspectService {

    @Override
    public QueryWrapper<ConmpanyInspectEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ConmpanyInspectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<ConmpanyInspectDTO> findConmpanyInspectBycomId(Long id) {
        return baseDao.findConmpanyInspectBycomId(id);
    }

    @Override
    public PageData<Map<String, Object>> screenCompanyInspectionNum(Map<String, Object> params) {
        IPage<Map<String, Object>> pageList = baseDao.screenCompanyInspectionNum(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<Map<String, Object>> objectPageData = new PageData<Map<String, Object>>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }
}