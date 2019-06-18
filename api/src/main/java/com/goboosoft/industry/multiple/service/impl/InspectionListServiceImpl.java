package com.goboosoft.industry.multiple.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.company.companyinspection.service.ConmpanyInspectService;
import com.goboosoft.industry.multiple.dao.BasicInspectionListDao;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.entity.BasicInspectionListEntity;
import com.goboosoft.industry.multiple.service.InspectionListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 检查项/检查内容信息管理
 * created by yangkun
 * created time 2019/2/14
 * class describe
 */
@Service
@Transactional
public class InspectionListServiceImpl extends CrudServiceImpl<BasicInspectionListDao, BasicInspectionListEntity, BasicInspectionListDTO> implements InspectionListService {

    @Autowired
    private ConmpanyInspectService conmpanyInspectService;
    @Override
    public QueryWrapper<BasicInspectionListEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BasicInspectionListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<BasicInspectionListEntity> getEntityListByLevelAndPid(String level, Long pid) {
        QueryWrapper<BasicInspectionListEntity> wr = new QueryWrapper<>();
        wr.eq("level", level);
        wr.eq("pid", pid);
        return baseDao.selectList(wr);
    }

    @Override
    public List<BasicInspectionListDTO> getListName(Long companyId,Long userId) {
        List<BasicInspectionListDTO> list = new ArrayList<BasicInspectionListDTO>();
        QueryWrapper<BasicInspectionListEntity> wrapper = new QueryWrapper<BasicInspectionListEntity>();
        wrapper.eq("level","0");
        wrapper.eq("pid","0");
        List<BasicInspectionListEntity> basicInspectionListEntities = baseDao.selectList(wrapper);
        for(BasicInspectionListEntity basicInspectionListEntity : basicInspectionListEntities){
            BasicInspectionListDTO basicInspectionListDTO = ConvertUtils.sourceToTarget(basicInspectionListEntity, BasicInspectionListDTO.class);
            Integer count = conmpanyInspectService.getCount(basicInspectionListDTO.getId(),companyId,userId);
            basicInspectionListDTO.setCount(count);
            list.add(basicInspectionListDTO);
        }
        return list;
    }

    public List<BasicInspectionListDTO> getListByPid(String pid){
        return baseDao.getListPid(pid);
    }

    @Override
    public List<BasicInspectionListDTO> findConmpanyInspectList(Long comId) {
        return baseDao.findConmpanyInspectList(comId);
    }
}
