package com.goboosoft.modules.syscompany.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.syscompany.dto.SysCompanyDTO;
import com.goboosoft.modules.syscompany.entity.SysCompanyEntity;

import java.util.Map;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
public interface SysCompanyService extends CrudService<SysCompanyEntity, SysCompanyDTO> {

    PageData<SysCompanyDTO> pageList(Map<String, Object> params);

    void audit(SysCompanyDTO dto);

    PageData<Map<String,Object>> pageScreenList(Map<String, Object> params);

    SysCompanyDTO findInspectionNum(Long companyId);
}