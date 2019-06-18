package com.goboosoft.industry.multiple.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.multiple.dto.SpotCheckCompanyDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
public interface SysCompanyService extends CrudService<SysCompanyEntity, SysCompanyDTO> {
    /**
     * 根据行业监管公司id查询下面所有被监管的公司
     */
    List<SpotCheckCompanyDTO> getCompanys(Map<String,Object> params);

    /**
     * 根据公司id获取公司详情
     * @param companyId
     * @return
     */
    SysCompanyDTO getByCompanyId(Long companyId);
}