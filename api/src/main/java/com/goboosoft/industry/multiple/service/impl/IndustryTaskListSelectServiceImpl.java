package com.goboosoft.industry.multiple.service.impl;

import com.goboosoft.industry.multiple.dao.IndustryTaskListSelectDao;
import com.goboosoft.industry.multiple.dto.IndustryTaskListSelectDTO;
import com.goboosoft.industry.multiple.service.IndustryTaskListSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * &#x884c;&#x4e1a; - &#x65b0;&#x4efb;&#x52a1;&#x53d1;&#x5e03;&#x8868;
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Service
public class IndustryTaskListSelectServiceImpl implements IndustryTaskListSelectService {
    @Autowired
    IndustryTaskListSelectDao industryTaskListSelectDao;
    /**
     * 行业新任务发布列表查询
     * @return
     */
    @Override
    public List<IndustryTaskListSelectDTO> getSendList(Map<String, Object> params) {
        List<IndustryTaskListSelectDTO> sendList=industryTaskListSelectDao.getSendList(params);
        return sendList;
    }
    /**
     * 行业新任务接收列表查询
     * @return
     */
    @Override
    public List<IndustryTaskListSelectDTO> getReceiveList(Map<String, Object> params) {
        List<IndustryTaskListSelectDTO> receiveList = industryTaskListSelectDao.getReceiveList(params);
        return receiveList;
    }
    /**
     * 行业新任务带确认列表查询
     * @return
     */
    @Override
    public List<IndustryTaskListSelectDTO> getBeConfirmedList(Map<String, Object> params) {
        List<IndustryTaskListSelectDTO> receiveList = industryTaskListSelectDao.getBeConfirmedList(params);
        return receiveList;
    }
    /**
     * 行业新任务接收数量查询
     * @return
     */
    @Override
    public Integer getReceiveListNum(Map<String, Object> params){
        List<IndustryTaskListSelectDTO> receiveList = industryTaskListSelectDao.getReceiveList(params);
        return receiveList.size();
    }
    /**
     * 查询任务待处理和超期及已完成列表
     * @return
     */
    @Override
    public List<IndustryTaskListSelectDTO> getHomePageList(Map<String, Object> params) {
        List<IndustryTaskListSelectDTO> list = new ArrayList<IndustryTaskListSelectDTO>();
            list = industryTaskListSelectDao.getHomePageList(params);
        return list;
    }
}