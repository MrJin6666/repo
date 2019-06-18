package com.goboosoft.industry.multiple.dao;


import com.goboosoft.industry.multiple.dto.InspectionItemsDTO;
import com.goboosoft.industry.multiple.dto.InspectionProjectDTO;
import com.goboosoft.industry.multiple.dto.SpotCheckListDTO;
import com.goboosoft.industry.multiple.dto.SpotCheckSampleListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 行业 - 企业抽查
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Mapper
public interface SpotCheckDao {
    /**
     * 选择检查项
     * @return
     */
    List<InspectionProjectDTO> getinspectionProject();
    /**
     * 查询检查内容
     * @return
     */
    List<InspectionItemsDTO> getinspectionitems();
    /**
     * 行业未上报列表查询
     * @return
     */
    List<SpotCheckSampleListDTO> getNotReportedList(@Param("params") Map<String, Object> params);
    /**
     * 行业已上报接收列表查询
     * @return
     */
    List<SpotCheckSampleListDTO> getReportedList(@Param("params") Map<String, Object> params);
    /**
     * 根据样本id查询订单详情
     */
    SpotCheckSampleListDTO getSpotCheckDetails(@Param("id")Long id);

    /**
     * 根据企业id查询抽查清单
     */
    List<SpotCheckListDTO> getspotCheckList(@Param("params") Map<String, Object> params);
}