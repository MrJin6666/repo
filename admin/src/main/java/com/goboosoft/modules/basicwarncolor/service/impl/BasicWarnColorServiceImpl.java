package com.goboosoft.modules.basicwarncolor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.basicwarncolor.dao.BasicWarnColorDao;
import com.goboosoft.modules.basicwarncolor.dto.BasicWarnColorDTO;
import com.goboosoft.modules.basicwarncolor.entity.BasicWarnColorEntity;
import com.goboosoft.modules.basicwarncolor.service.BasicWarnColorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 过期警告颜色
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Service
public class BasicWarnColorServiceImpl extends CrudServiceImpl<BasicWarnColorDao, BasicWarnColorEntity, BasicWarnColorDTO> implements BasicWarnColorService {

    @Override
    public QueryWrapper<BasicWarnColorEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BasicWarnColorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}