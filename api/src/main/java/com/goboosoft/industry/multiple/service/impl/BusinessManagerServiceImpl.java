package com.goboosoft.industry.multiple.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.company.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.company.companymanagelog.entity.CompanyManageLogEntity;
import com.goboosoft.company.companymanagelog.service.CompanyManageLogService;
import com.goboosoft.industry.multiple.dao.BusinessManagerDao;
import com.goboosoft.industry.multiple.dto.BusinessDTO;
import com.goboosoft.industry.multiple.dto.BusinessItemDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import com.goboosoft.industry.multiple.service.BusinessManagerService;
import com.goboosoft.system.security.user.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 企业管理
 *
 * @author jinxin
 */
@Service
public class BusinessManagerServiceImpl extends BaseServiceImpl<BusinessManagerDao, SysCompanyEntity> implements BusinessManagerService {
    @Autowired
    private BusinessManagerDao businessManagerDao;
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Autowired
    private CompanyManageLogService companyManageLogService;

    /**
     * 根据企业检查状态查询企业
     *
     * @param params
     * @return
     */
    @Override
    public List<BusinessDTO> queryCompanyByCheckStatus(Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId", userId);
        return businessManagerDao.queryAllCompany(params);

    }

    /**
     * 查询企业详情
     *
     * @param companyId
     * @return
     */
    @Override
    public BusinessItemDTO queryCompanyItemByCompanyId(String companyId) {
        BusinessItemDTO businessItemDTO = businessManagerDao.queryCompanyItemByCompanyId(companyId);
        long id = Long.parseLong(companyId);
        List<ConmpanyInspectDTO> listNameAndNum = conmpanyInspectService.getListNameAndNum(id);
        businessItemDTO.setList(listNameAndNum);
        return businessItemDTO;
    }

    /**
     * 根据公司状态查询公司列表
     *
     * @param params
     * @return
     */
    @Override
    public List<BusinessDTO> queryCompanyListByStatus(Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId", userId);
        return businessManagerDao.queryCompanyListByStatus(params);

    }

    /**
     * 根据公司id查询公司记录
     *
     * @param  companyId
     * @return
     */
    @Override
    public List<CompanyManageLogEntity> queryCompanyRecord(String companyId) {
        long id = Long.parseLong(companyId);
        List<CompanyManageLogEntity> lists = companyManageLogService.getLists(id);
        return lists ;
    }

    /**
     * 查询待审批企业总数
     * @return
     */
    @Override
    public Integer queryNoApproalNumber() {
        Long userId = SecurityUser.getUserId();
         return  businessManagerDao.queryNoApproalNumber(userId);
    }
}
