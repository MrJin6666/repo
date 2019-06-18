package com.goboosoft.industry.homepage.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.common.utils.DateUtils;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.company.govern.entity.GovernPlanEntity;
import com.goboosoft.industry.company.service.QueryCompanyListService;
import com.goboosoft.industry.govern.dao.GovernDao;
import com.goboosoft.industry.govern.dto.ExceedGovernDTO;
import com.goboosoft.industry.govern.dto.GovernDTO;
import com.goboosoft.industry.govern.service.GovernService;
import com.goboosoft.industry.homepage.dto.EnterprisesTotalDTO;
import com.goboosoft.industry.homepage.dto.TaskTotalDTO;
import com.goboosoft.industry.homepage.service.EnterprisesTotalService;
import com.goboosoft.industry.multiple.dto.IndustryTaskListSelectDTO;
import com.goboosoft.industry.multiple.service.IndustryTaskListSelectService;
import com.goboosoft.industry.supervisionandinspection.service.BusinInspectionTaskService;
import com.goboosoft.system.security.user.SecurityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnterprisesTotalServiceImpl implements EnterprisesTotalService {
    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Autowired
    private QueryCompanyListService queryCompanyListService;
    @Autowired
    private IndustryTaskListSelectService industryTaskListSelectService;
    @Autowired
    private BusinInspectionTaskService businInspectionTaskService;
    private static final Long  BAKELACQUERId =1103472282774237185L;
    private static final Long  PAINTID = 1103472282774237186L;
    private static final Long  POLISHINGId = 1103472282774237187L;
    private static final Long  WASTEId = 1103484049910902788L;

    /**
     *企业总数统计
     * @return
     */
    @Override
    public EnterprisesTotalDTO getcompanyCount() {
        //获取烤漆房，危废间等数量
        Long userId = SecurityUser.getUserId();
        List<ConmpanyInspectDTO> inspectlists = conmpanyInspectService.getInspectCountByGroup(userId);
        //获取企业总数
        Long companyTotle = queryCompanyListService.getCompanyTotle(userId);
        EnterprisesTotalDTO enterprisesTotalDTO = new EnterprisesTotalDTO();
        enterprisesTotalDTO.setCompanyNum(companyTotle);
        enterprisesTotalDTO.setInspectList(inspectlists);
        return enterprisesTotalDTO;
    }
    /**
     * 首页任务数量统计
     */
    @Override
    public TaskTotalDTO getTaskTotal(){
        //处理待处理
        Map<String, Object> params = new HashMap<>();
        Long userId = SecurityUser.getUserId();
        params.put("userId",userId);
        params.put("status",0);
        List<IndustryTaskListSelectDTO> beProcessedList= industryTaskListSelectService.getHomePageList(params);
        //处理超期
        Map<String, Object> params1 = new HashMap<>();
        params1.put("userId",userId);
        params1.put("status",5);
        List<IndustryTaskListSelectDTO> beyondTimeList = industryTaskListSelectService.getHomePageList(params1);
        //处理已完成
        Map<String, Object> params2 = new HashMap<>();
        params2.put("userId",userId);
        params2.put("status",3);
        List<IndustryTaskListSelectDTO> completedList = industryTaskListSelectService.getHomePageList(params2);
        Integer businInspectionNum =businInspectionTaskService.getCheckCountByUserId();
        TaskTotalDTO taskTotalDTO = new TaskTotalDTO();
        if(beProcessedList!=null){
            taskTotalDTO.setBeProcessedNum(beProcessedList.size()+businInspectionNum);
        }else{
            taskTotalDTO.setBeProcessedNum(0);
        }
        if(beyondTimeList!=null){
            taskTotalDTO.setBeyondTimeNum(beyondTimeList.size());
        }else{
            taskTotalDTO.setBeyondTimeNum(0);
        }
        if(completedList!=null){
            taskTotalDTO.setCompletedNum(completedList.size());
        }else{
            taskTotalDTO.setBeyondTimeNum(0);
        }
        return taskTotalDTO;
    }
}
