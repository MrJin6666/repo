package com.goboosoft.modules.companymenuuser.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.companymenuuser.dto.CompanyUserMenuDTO;
import com.goboosoft.modules.companymenuuser.entity.CompanyUserMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
public interface CompanyUserMenuService extends CrudService<CompanyUserMenuEntity, CompanyUserMenuDTO> {

    List<CompanyUserMenuEntity> getByUserId(Long userId);

    void saveModule(Map<String, Object> map);

    Integer deleteByUserId(Long id);
}