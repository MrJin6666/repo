package com.goboosoft.industry.govern.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.govern.dao.GovernDao;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GovernDTO;
import com.goboosoft.industry.govern.dto.GovernItemDTO;
import com.goboosoft.industry.govern.dto.GuideExceedDTO;
import com.goboosoft.industry.govern.service.GovernService;
import com.goboosoft.system.security.user.SecurityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GovernServiceImpl extends BaseServiceImpl<GovernDao,GovernPlanEntity> implements GovernService {
    @Autowired
    private GovernDao governDao;
    @Autowired
    private GovernService governService;

    /**
     * 当日治理
     * @param params
     * @return
     */
    @Override
    public List<GovernDTO> queryCompanyName(@Param("params") Map<String, Object> params) {
        Date date = new Date();
        String dayDate = DateUtils.format(date, "yyyy-MM-dd");
        StringBuilder stringBuilder=new StringBuilder();
        String todayDate = stringBuilder.append("%").append(dayDate).append("%").toString();
        params.put("todayDate",todayDate);
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        return governDao.queryCompanyName(params);
    }

    /**
     * 超期企业列表
     * @param params
     * @return
     */
    @Override
    public List<ExceedGovernDTO> queryExceedCompanyName(Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        List<ExceedGovernDTO> exceedcCompanyList = governDao.queryExceedCompanyName(params);
        for (ExceedGovernDTO exceedGovernDTO : exceedcCompanyList) {
            Long companyId = exceedGovernDTO.getCompanyId();
            params.put("companyId",companyId);
            params.put("status",4);
            params.put("lastRowNum",null);
            params.put("pageSize",null);
            List<GuideExceedDTO> guideExceedDTOS = governService.queryGuideExceedListByStatus(params);
            int exceedCount = guideExceedDTOS.size();
            exceedGovernDTO.setExceedCount(exceedCount);
        }
        Collections.sort(exceedcCompanyList);
        return exceedcCompanyList;
    }

    /**
     * 治理档案企业名称列表
     * @param params
     * @return
     */
    @Override
    public List<QueryCompanyListDTO> queryGovernRecordCompanyList(Map<String, Object> params) {
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        return governDao.queryGovernRecordCompanyList(params);
    }

    /**
     * 通过企业id和企业治理状态查询列表
     * @param params
     * @return
     */
    @Override
    public List<GuideExceedDTO> queryGuideExceedListByStatus(Map<String, Object> params) {
        return governDao.queryGuideExceedListByStatus(params);
    }

    /**
     * 根据公司id查询公司超期详情
     * @param params
     * @return
     */
    @Override
    public List<GovernItemDTO> queryExceedCompanyItem(Map<String, Object> params) {
        return governDao.queryExceedCompanyItem(params);
    }
    /**
     * 根据公司id查询公司超期详情
     * @param params
     * @return
     */
    @Override
    public List<GovernItemDTO> queryTodayCompanyItem(Map<String, Object> params) {
        Date date = new Date();
        String dayDate = DateUtils.format(date, "yyyy-MM-dd");
        StringBuilder stringBuilder=new StringBuilder();
        String todayDate = stringBuilder.append("%").append(dayDate).append("%").toString();
        params.put("todayDate",todayDate);
        return governDao.queryTodayCompanyItem(params);
    }


}
