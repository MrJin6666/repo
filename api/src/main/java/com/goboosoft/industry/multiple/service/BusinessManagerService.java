package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.entity.CompanyManageLogEntity;
import com.goboosoft.industry.multiple.dto.BusinessDTO;
import com.goboosoft.industry.multiple.dto.BusinessItemDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;

import java.util.List;
import java.util.Map;

/**
 * 企业管理
 * @author jinxin
 * created time 2019/3/11
 */
public interface BusinessManagerService extends BaseService<SysCompanyEntity> {
    /**
     * 根据企业检查状态查询企业
     * @return
     */
    List<BusinessDTO> queryCompanyByCheckStatus(Map<String,Object> pMap);

    /**
     * 根据企业id查询企业详情
     * @param companyId
     * @return
     */
    BusinessItemDTO queryCompanyItemByCompanyId(String companyId);

    /**
     * 根据企业审核状态查询企业列表
     * @param prams
     * @return
     */
    List<BusinessDTO> queryCompanyListByStatus(Map<String,Object> prams);

    /**
     * 根据企业id状态查询企业记录
     * @param  companyId
     * @return
     */
    List<CompanyManageLogEntity> queryCompanyRecord(String companyId);

    /**
     * 查询待审批企业总数
     * @return
     */
    Integer queryNoApproalNumber();
}
