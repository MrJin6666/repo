package com.goboosoft.modules.supervisionandinspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.supervisionandinspection.dao.BusinInspectionTaskDao;
import com.goboosoft.modules.supervisionandinspection.dto.BusinInspectionTaskDTO;
import com.goboosoft.modules.supervisionandinspection.entity.BusinInspectionTaskEntity;
import com.goboosoft.modules.supervisionandinspection.service.BusinInspectionTaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Service
public class BusinInspectionTaskServiceImpl extends CrudServiceImpl<BusinInspectionTaskDao, BusinInspectionTaskEntity, BusinInspectionTaskDTO> implements BusinInspectionTaskService {


    @Override
    public QueryWrapper<BusinInspectionTaskEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        Long userId = (Long)params.get("userId");

        QueryWrapper<BusinInspectionTaskEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq("inspect_user_id", userId);

        return wrapper;
    }

    @Override
    public PageData<Map<String, Object>> screenDd(Map<String, Object> params) {
        IPage<Map<String, Object>> pageList = baseDao.screenDd(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<Map<String, Object>> objectPageData = new PageData<Map<String, Object>>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }
}