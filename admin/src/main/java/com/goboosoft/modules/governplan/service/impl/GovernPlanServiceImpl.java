package com.goboosoft.modules.governplan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.governplan.dao.GovernPlanDao;
import com.goboosoft.modules.governplan.dto.GovernPlanDTO;
import com.goboosoft.modules.governplan.entity.GovernPlanEntity;
import com.goboosoft.modules.governplan.service.GovernPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 治理计划
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Service
public class GovernPlanServiceImpl extends CrudServiceImpl<GovernPlanDao, GovernPlanEntity, GovernPlanDTO> implements GovernPlanService {

    @Override
    public QueryWrapper<GovernPlanEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GovernPlanEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<GovernPlanDTO> pageList(Map<String, Object> params) {
        IPage<GovernPlanDTO> pageList = baseDao.findPageList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<GovernPlanDTO> objectPageData = new PageData<GovernPlanDTO>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }

    @Override
    public PageData<Map<String, Object>> screenCompanyPlan(Map<String, Object> params) {
        IPage<Map<String, Object>> pageList = baseDao.screenCompanyPlan(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<Map<String, Object>> objectPageData = new PageData<Map<String, Object>>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }

    @Override
    public List<BasicInspectionListDTO> getNoProducePlan(Map<String, Object> params){
        return baseDao.getNoProducePlan(params);
    }
}