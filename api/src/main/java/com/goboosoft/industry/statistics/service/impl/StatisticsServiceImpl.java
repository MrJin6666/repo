package com.goboosoft.industry.statistics.service.impl;

import com.goboosoft.industry.statistics.dao.StatisticsDao;
import com.goboosoft.industry.statistics.dto.CommonProblemsDTO;
import com.goboosoft.industry.statistics.dto.SupervisePeopleDTO;
import com.goboosoft.industry.statistics.service.StatisticsService;
import com.goboosoft.industry.supervisionandinspection.dao.BusinInspectionTaskDao;
import com.goboosoft.industry.supervisionandinspection.dao.BusinInspectionTaskItemContentDao;
import com.goboosoft.industry.supervisionandinspection.dto.BusinInspectionTaskDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private BusinInspectionTaskDao businInspectionTaskDao;
    @Autowired
    BusinInspectionTaskItemContentDao businInspectionTaskItemContentDao;
    /**
     * 查询督导人次
     * @param params
     * @return
     */
    public List<SupervisePeopleDTO> getSupervisePeople(Map<String, Object> params){
        List<SupervisePeopleDTO> supervisePeopleList= businInspectionTaskDao.getSupervisePeople(params);
        return supervisePeopleList;
    }
    /**
     * 查询常见问题统计
     * @param params
     * @return
     */
    public List<CommonProblemsDTO> getProblemsList(Map<String, Object> params){
        List<CommonProblemsDTO> problemsLists = businInspectionTaskItemContentDao.getProblemsList(params);
        return problemsLists;
    }
}