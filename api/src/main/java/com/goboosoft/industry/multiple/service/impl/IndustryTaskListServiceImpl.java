package com.goboosoft.industry.multiple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.industry.multiple.dao.IndustryTaskListDao;
import com.goboosoft.industry.multiple.dto.IndustryTaskListDTO;
import com.goboosoft.industry.multiple.entity.IndustryTaskListEntity;
import com.goboosoft.industry.multiple.service.IndustryTaskListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * &#x884c;&#x4e1a; - &#x65b0;&#x4efb;&#x52a1;&#x53d1;&#x5e03;&#x8868;
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Service
public class IndustryTaskListServiceImpl extends CrudServiceImpl<IndustryTaskListDao, IndustryTaskListEntity, IndustryTaskListDTO> implements IndustryTaskListService {

    @Override
    public QueryWrapper<IndustryTaskListEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<IndustryTaskListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    /**
     * 查询已存在的最新订单号
     */
    @Override
    public   String orderNumberSelect(){
        String  lastNumber = baseDao.orderNumberSelect();
        return lastNumber;
    }

}