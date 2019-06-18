package com.goboosoft.industry.multiple.service;

import com.goboosoft.industry.multiple.dto.IndustryTaskListSelectDTO;

import java.util.List;
import java.util.Map;

/**
 * 新任务发布列表查询
 * @author yuzhao
 * created time 2019/3/7
 */
public interface IndustryTaskListSelectService {
    /**
     * 行业新任务发布列表查询
     * @return
     */
    List<IndustryTaskListSelectDTO> getSendList(Map<String, Object> params);
    /**
     * 行业新任务接收列表查询
     * @return
     */
    List<IndustryTaskListSelectDTO> getReceiveList(Map<String, Object> params);
    /**
     * 行业新任务待确认列表查询
     * @return
     */
    List<IndustryTaskListSelectDTO> getBeConfirmedList(Map<String, Object> params);
    /**
     * 行业新任务接收数量查询
     */
    Integer getReceiveListNum(Map<String, Object> params);
    /**
     * 查询任务待处理和超期及已完成列表
     * @return
     */
    List<IndustryTaskListSelectDTO> getHomePageList(Map<String, Object> params);
}
