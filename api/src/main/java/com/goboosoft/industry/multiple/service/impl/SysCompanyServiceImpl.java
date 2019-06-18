package com.goboosoft.industry.multiple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.industry.multiple.dao.SysCompanyDao;
import com.goboosoft.industry.multiple.dto.SpotCheckCompanyDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Service
public class SysCompanyServiceImpl extends CrudServiceImpl<SysCompanyDao, SysCompanyEntity, SysCompanyDTO> implements SysCompanyService {
    @Override
    public QueryWrapper<SysCompanyEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SysCompanyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
    /**
     * 根据行业监管公司id查询下面所有被监管的公司
     */
    public List<SpotCheckCompanyDTO> getCompanys(Map<String, Object> params){
        List<SpotCheckCompanyDTO> Companylist= baseDao.getCompanys(params);
        return Companylist;
    }
    @Override
    public SysCompanyDTO getByCompanyId(Long companyId){
        return baseDao.getByCompanyId(companyId);
    }
}