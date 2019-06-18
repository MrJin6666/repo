package com.goboosoft.modules.companymenuuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.companymenuuser.dao.CompanyUserMenuDao;
import com.goboosoft.modules.companymenuuser.dto.CompanyUserMenuDTO;
import com.goboosoft.modules.companymenuuser.entity.CompanyUserMenuEntity;
import com.goboosoft.modules.companymenuuser.service.CompanyMenuService;
import com.goboosoft.modules.companymenuuser.service.CompanyUserMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Service
public class CompanyUserMenuServiceImpl extends CrudServiceImpl<CompanyUserMenuDao, CompanyUserMenuEntity, CompanyUserMenuDTO> implements CompanyUserMenuService {

    @Autowired
    private CompanyMenuService companyMenuService;

    @Override
    public QueryWrapper<CompanyUserMenuEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CompanyUserMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<CompanyUserMenuEntity> getByUserId(Long userId){
        QueryWrapper<CompanyUserMenuEntity> wrapper = new QueryWrapper<CompanyUserMenuEntity>();
        wrapper.eq("user_id",userId);
        List<CompanyUserMenuEntity> companyUserMenuEntities = baseDao.selectList(wrapper);
        return companyUserMenuEntities;
    }

    @Override
    public void saveModule(Map<String, Object> map){
        Long userId = (Long)map.get("userId");
        List<String> modiles = (List) map.get("list");
        QueryWrapper<CompanyUserMenuEntity> wrapper = new QueryWrapper<CompanyUserMenuEntity>();
        wrapper.eq("user_id",userId);
        int delete = baseDao.delete(wrapper);
        if(modiles!=null) {
            for (String module : modiles) {
                CompanyUserMenuEntity companyUserMenuEntity = new CompanyUserMenuEntity();
                companyUserMenuEntity.setCompanyMenuModule(module);
                companyUserMenuEntity.setUserId(userId);
                baseDao.insert(companyUserMenuEntity);
            }
        }
    }

    @Override
    public Integer deleteByUserId(Long userId){
        QueryWrapper<CompanyUserMenuEntity> wrapper = new QueryWrapper<CompanyUserMenuEntity>();
        wrapper.eq("user_id",userId);
        return baseDao.delete(wrapper);
    }
}