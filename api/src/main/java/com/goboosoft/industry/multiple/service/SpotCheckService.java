package com.goboosoft.industry.multiple.service;


import com.alibaba.fastjson.JSONObject;
import com.goboosoft.industry.multiple.dto.*;

import java.util.List;
import java.util.Map;

/**
 * 行业 - 抽查任务接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
public interface SpotCheckService {
    /**
     *企业抽查样本建立
     */
    void saveSample(JSONObject jsonObject /*List<Map<String,Object>> list,List<InspectionProjectDTO> inspectionProjectDtoList*/);
    /**
     * 选择检查项
     * @return
     */
    List<InspectionProjectDTO> getinspectionProject();
    /**
     * 获取未上报
     * @return
     */
    List<SpotCheckSampleListDTO> getNotReportedList(Map<String, Object> params);
    /**
     * 获取已上报
     * @return
     */
    List<SpotCheckSampleListDTO> getReportedList(Map<String, Object> params);
    /**
     * 根据订单号查询订单详情
     */
    SpotCheckSampleListDTO getSpotCheckDetails(Long id);
    /**
     * 根据企业id查询抽查清单
     */
    List<SpotCheckListDTO> getspotCheckList(Map<String, Object> params);
}