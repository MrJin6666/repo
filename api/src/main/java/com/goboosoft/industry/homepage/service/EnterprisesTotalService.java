package com.goboosoft.industry.homepage.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GovernDTO;
import com.goboosoft.industry.homepage.dto.EnterprisesTotalDTO;
import com.goboosoft.industry.homepage.dto.TaskTotalDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EnterprisesTotalService {
    /**
     * 企业统计
     * @return
     */
    EnterprisesTotalDTO getcompanyCount();
    /**
     * 首页任务数量统计
     */
    TaskTotalDTO getTaskTotal();
}
