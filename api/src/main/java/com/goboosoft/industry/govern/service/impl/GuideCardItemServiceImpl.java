package com.goboosoft.industry.govern.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.industry.govern.dao.GuideCardItemDao;
import com.goboosoft.industry.govern.dto.GuideCardItemDTO;
import com.goboosoft.industry.govern.service.GuideCardItemService;
import com.goboosoft.industry.supervisionandinspection.entity.BusinInspectionTaskItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GuideCardItemServiceImpl extends BaseServiceImpl<GuideCardItemDao,BusinInspectionTaskItemEntity> implements GuideCardItemService{
    @Autowired
    private GuideCardItemDao guideCardItemDao;
    @Override
    public List<GuideCardItemDTO> queryGuideCardItemBycheckStatus(Map<String, Object> params) {
        return guideCardItemDao.queryGuideCardItemBycheckStatus(params);
    }
}
