package com.goboosoft.industry.govern.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GovernDTO;
import com.goboosoft.industry.govern.dto.GovernItemDTO;
import com.goboosoft.industry.govern.dto.GuideExceedDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GovernService extends BaseService<GovernPlanEntity> {

    List<GovernDTO> queryCompanyName(@Param("params") Map<String, Object> params);

    List<ExceedGovernDTO> queryExceedCompanyName(@Param("params") Map<String,Object> params);

    List<QueryCompanyListDTO> queryGovernRecordCompanyList( @Param("params") Map<String,Object> params);

    List<GuideExceedDTO> queryGuideExceedListByStatus(@Param("params") Map<String,Object> params);

    /**
     * 查询超期企业详情
     * @param params
     * @return
     */
    List<GovernItemDTO> queryExceedCompanyItem(@Param("params") Map<String,Object> params);

    /**
     * 查询当日治理企业详情
     * @param params
     * @return
     */
    List<GovernItemDTO> queryTodayCompanyItem(@Param("params")Map<String,Object> params);
}
