package com.goboosoft.company.managesystem.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.managesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;

import java.util.Map;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-08
 */
public interface CompanyManageSystemService extends
    CrudService<CompanyManageSystemEntity, CompanyManageSystemDTO> {

    PageData<CompanyManageSystemDTO> pageLists(Map<String, Object> params);
}