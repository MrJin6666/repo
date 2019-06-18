package com.goboosoft.company.companymenuuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.companymenuuser.dao.CompanyMenuDao;
import com.goboosoft.company.companymenuuser.dto.CompanyMenuDTO;
import com.goboosoft.company.companymenuuser.entity.CompanyMenuEntity;
import com.goboosoft.company.companymenuuser.service.CompanyMenuService;
import com.goboosoft.company.companymenuuser.service.CompanyUserMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Service
public class CompanyMenuServiceImpl extends CrudServiceImpl<CompanyMenuDao, CompanyMenuEntity, CompanyMenuDTO> implements CompanyMenuService {

    @Autowired
    private CompanyUserMenuService companyUserMenuService;

    @Override
    public QueryWrapper<CompanyMenuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CompanyMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public void setMenuByUserId(Map<String,Object> map){
        companyUserMenuService.saveModule(map);
    }

    @Override
    public List<CompanyMenuDTO> selectAll(){
        return baseDao.selectAll();
    }

}