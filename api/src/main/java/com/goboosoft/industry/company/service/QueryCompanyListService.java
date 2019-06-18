package com.goboosoft.industry.company.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.industry.company.dto.QueryCompanyListDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * 获取公司列表（带权限）
 *
 * @author jinxin
 */
public interface QueryCompanyListService extends BaseService<SysCompanyEntity> {
    //获取带权限的企业总数
    Long getCompanyTotle(Long userId);
    /**
     *获取当前登录人所涉及企业列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyList(@Param("params") Map<String, Object> params);

    /**
     * 通过企业状态查询企业名称列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyListByStatus(@Param("params") Map<String, Object> params);
    /**
     * 通过企业检查状态查询企业名称列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyListByCheckStatus(@Param("params") Map<String, Object> params);


    /**
     * 通过治理状态查询企业名称列表
     * @param params
     * @return
     */
    List<QueryCompanyListDTO> queryCompanyListByGovernStatus(@Param("params") Map<String, Object> params);
}
