package com.goboosoft.industry.statistics.service;


import com.goboosoft.industry.statistics.dto.CommonProblemsDTO;
import com.goboosoft.industry.statistics.dto.SupervisePeopleDTO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    /**
     * 查询督导人次
     */
    List<SupervisePeopleDTO> getSupervisePeople(Map<String, Object> params);
    /**
     * 常见问题统计
     */
    List<CommonProblemsDTO> getProblemsList(Map<String, Object> params);
}
