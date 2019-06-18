package com.goboosoft.industry.govern.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GovernDTO;
import com.goboosoft.industry.govern.dto.GovernItemDTO;
import com.goboosoft.industry.govern.dto.GuideExceedDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface GovernDao extends BaseDao<GovernPlanEntity> {
    /**
     * 当日治理
     * @param params
     * @return
     */
    List<GovernDTO> queryCompanyName(@Param("params") Map<String, Object> params);


    /**
     * 超期
     * @param params
     * @return
     */
    List<ExceedGovernDTO> queryExceedCompanyName(@Param("params")Map<String,Object> params);

    /**
     * 治理档案企业名称列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryGovernRecordCompanyList(@Param("params") Map<String,Object> params);

    /**
     * 根据公司id和治理状态查询列表
     * @param params
     * @return
     */
    List<GuideExceedDTO> queryGuideExceedListByStatus(@Param("params") Map<String,Object> params);

    /**
     * 根据公司id查询公司超期详情
     * @param params
     * @return
     */
    List<GovernItemDTO> queryExceedCompanyItem(@Param("params") Map<String,Object> params);
    /**
     * 根据公司id查询公司当日治理详情
     * @param params
     * @return
     */
    List<GovernItemDTO> queryTodayCompanyItem(@Param("params") Map<String,Object> params);
}
