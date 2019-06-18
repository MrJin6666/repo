package com.goboosoft.modules.basicinspectionsteps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.basicinspectionsteps.dao.BasicInspectionStepsDao;
import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import com.goboosoft.modules.basicinspectionsteps.entity.BasicInspectionStepsEntity;
import com.goboosoft.modules.basicinspectionsteps.service.BasicInspectionStepsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Service
public class BasicInspectionStepsServiceImpl extends CrudServiceImpl<BasicInspectionStepsDao, BasicInspectionStepsEntity, BasicInspectionStepsDTO> implements BasicInspectionStepsService {

    @Override
    public QueryWrapper<BasicInspectionStepsEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BasicInspectionStepsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public void deleteByInspectionListId(Long id) {
        baseDao.deleteByInspectionListId(id);
    }

    @Override
    public List<BasicInspectionStepsDTO> getByInspectionListId(Long id) {
        return baseDao.getByInspectionListId(id);
    }
}