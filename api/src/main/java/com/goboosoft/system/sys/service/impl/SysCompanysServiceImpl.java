package com.goboosoft.system.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.system.sys.dao.SysCompanysDao;
import com.goboosoft.system.sys.dto.SysCompanyDTO;
import com.goboosoft.system.sys.entity.SysCompanyEntity;
import com.goboosoft.system.sys.service.SysCompanysService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 基础 - 公司信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-18
 */
@Service
public class SysCompanysServiceImpl extends CrudServiceImpl<SysCompanysDao, SysCompanyEntity, SysCompanyDTO> implements SysCompanysService {

    @Override
    public QueryWrapper<SysCompanyEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SysCompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}