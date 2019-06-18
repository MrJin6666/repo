package com.goboosoft.modules.basicinspectionlist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.modules.basicinspectionlist.dao.BasicInspectionListDao;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.basicinspectionlist.entity.BasicInspectionListEntity;
import com.goboosoft.modules.basicinspectionlist.service.BasicInspectionListService;
import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import com.goboosoft.modules.basicinspectionsteps.service.BasicInspectionStepsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Service
public class BasicInspectionListServiceImpl extends CrudServiceImpl<BasicInspectionListDao, BasicInspectionListEntity, BasicInspectionListDTO> implements BasicInspectionListService {

    @Autowired
    private BasicInspectionStepsService basicInspectionStepsService;

    @Override
    public QueryWrapper<BasicInspectionListEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BasicInspectionListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<BasicInspectionListDTO> pageList(Map<String, Object> params) {
        IPage<BasicInspectionListDTO> pageList = baseDao.findPageList(getPage(params, Constant.CREATE_DATE, false),params);
        List<BasicInspectionListDTO> records = pageList.getRecords();
        if(records != null && records.size() >0){
            for (int i = 0; i < records.size(); i++) {
                BasicInspectionListDTO dto = records.get(i);
                List<BasicInspectionListDTO> list = baseDao.findAllByPid(dto.getId(),(String)params.get("level"));
                dto.setChildren(list);
            }
        }
        PageData<BasicInspectionListDTO> objectPageData = new PageData<BasicInspectionListDTO>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }

    @Override
    public void deleteByPid(Long pid) {
        baseDao.deleteByPid(pid);
    }

    @Override
    public List<BasicInspectionListDTO> findConmpanyInspectList(Long comId) {
        return baseDao.findConmpanyInspectList(comId);
    }

    @Override
    public List<BasicInspectionListDTO> companyList(Map<String, Object> params) {
        return baseDao.companyList(params);
    }

    @Override
    @Transactional
    public void saveDTO(BasicInspectionListDTO dto) {
        BasicInspectionListEntity basicInspectionListEntity = ConvertUtils.sourceToTarget(dto, BasicInspectionListEntity.class);
        this.insert(basicInspectionListEntity);
        List<BasicInspectionStepsDTO> stepsList = dto.getStepsList();
        if(stepsList != null && stepsList.size() >0){
            for (int i = 0; i < stepsList.size(); i++) {
                BasicInspectionStepsDTO basicInspectionStepsDTO = stepsList.get(i);
                basicInspectionStepsDTO.setSort(Long.valueOf(i));
                basicInspectionStepsDTO.setInspectionListId(basicInspectionListEntity.getId());
                basicInspectionStepsService.save(basicInspectionStepsDTO);
            }
        }
    }

    @Override
    @Transactional
    public void updateDTO(BasicInspectionListDTO dto) {
        if(!dto.getPid().equals(0)){
            // 先删除治理计划
            basicInspectionStepsService.deleteByInspectionListId(dto.getId());
            // 新增治理计划
            List<BasicInspectionStepsDTO> stepsList = dto.getStepsList();
            if(stepsList != null && stepsList.size() >0){
                for (int i = 0; i < stepsList.size(); i++) {
                    BasicInspectionStepsDTO basicInspectionStepsDTO = stepsList.get(i);
                    basicInspectionStepsDTO.setSort(Long.valueOf(i));
                    basicInspectionStepsDTO.setInspectionListId(dto.getId());
                    basicInspectionStepsService.save(basicInspectionStepsDTO);
                }
            }
        }
        this.update(dto);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        // 单个删除
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            if(null != id){
                basicInspectionStepsService.deleteByInspectionListId(id);
                this.deleteByPid(id);
            }
        }
        this.delete(ids);
    }
}