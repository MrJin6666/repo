package com.goboosoft.modules.companymanagesystem.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.companymanagesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.modules.companymanagesystem.entity.CompanyManageSystemEntity;

import java.util.Map;

/**
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
 */
public interface CompanyManageSystemService extends CrudService<CompanyManageSystemEntity, CompanyManageSystemDTO> {

    PageData<CompanyManageSystemDTO> pageList(Map<String, Object> params);
}