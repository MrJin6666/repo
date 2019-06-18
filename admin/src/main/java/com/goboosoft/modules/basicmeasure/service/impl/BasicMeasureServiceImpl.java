package com.goboosoft.modules.basicmeasure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.basicmeasure.dao.BasicMeasureDao;
import com.goboosoft.modules.basicmeasure.dto.BasicMeasureDTO;
import com.goboosoft.modules.basicmeasure.entity.BasicMeasureEntity;
import com.goboosoft.modules.basicmeasure.service.BasicMeasureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 计量单位
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Service
public class BasicMeasureServiceImpl extends CrudServiceImpl<BasicMeasureDao, BasicMeasureEntity, BasicMeasureDTO> implements BasicMeasureService {

    @Override
    public QueryWrapper<BasicMeasureEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String name = (String)params.get("name");

        QueryWrapper<BasicMeasureEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.like(StringUtils.isNotBlank(name),"name",name);
        return wrapper;
    }


}