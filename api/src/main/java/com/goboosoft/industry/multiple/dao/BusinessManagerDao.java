package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.dto.BusinessDTO;
import com.goboosoft.industry.multiple.dto.BusinessItemDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 企业管理
 *
 * @author jinxin
 */
@Mapper
@Repository
public interface BusinessManagerDao extends BaseDao<SysCompanyEntity> {
    /**
     * 根据企业检查状态查询全部企业
     *
     * @param params
     * @return
     */
    List<BusinessDTO> queryAllCompany(@Param("params") Map<String, Object> params);


    /**
     * 根据公司审核状态查询公司列表
     *
     * @param params
     * @return
     */
    List<BusinessDTO> queryCompanyListByStatus(@Param("params") Map<String, Object> params);

    /**
     * 根据公司id查询公司详情
     *
     * @param companyId
     * @return
     */
    BusinessItemDTO queryCompanyItemByCompanyId(@Param("companyId") String companyId);

    /**
     * 查询待审批企业总数
     * @param userId
     * @return
     */
    Integer queryNoApproalNumber(@Param("userId") Long userId);
}
