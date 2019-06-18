package com.goboosoft.industry.company.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 获取公司列表（带权限）
 *
 * @author jinxin
 */
@Mapper
@Repository
public interface QueryCompanyListDao extends BaseDao<SysCompanyEntity> {
    /**
     * 获取当前登录人所涉及企业列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyList(@Param("params") Map<String, Object> params);
    Long getCompanyTotle(@Param("userId")Long userId);

    /**
     * 根据企业状态查询企业名称列表
     * @param params
     * @return
     */

    List<QueryCompanyListDTO> queryCompanyListByStatus(@Param("params") Map<String, Object> params);

    /**
     * 根据检查状态查询企业名称列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyListByCheckStatus(@Param("params") Map<String, Object> params);

    Long getCompanyTotle();

    /**
     * 通过治理状态查询企业名称列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyListByGovernStatus(@Param("params") Map<String, Object> params);
}
