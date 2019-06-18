package com.goboosoft.company.companymenuuser.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.companymenuuser.dto.CompanyMenuDTO;
import com.goboosoft.company.companymenuuser.entity.CompanyMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
public interface CompanyMenuService extends CrudService<CompanyMenuEntity, CompanyMenuDTO> {

    /*
     * 第一次注册时，设置菜单权限
     */
    void setMenuByUserId(Map<String,Object> map);
    /*
     * 查询所有权限
     */
    List<CompanyMenuDTO> selectAll();
}