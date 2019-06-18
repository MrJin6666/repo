package com.goboosoft.industry.multiple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.industry.multiple.dao.BasicInspectionListDao;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.entity.BasicInspectionListEntity;
import com.goboosoft.industry.multiple.service.BasicInspectionListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-11
 */
@Service
public class BasicInspectionListServiceImpl extends CrudServiceImpl<BasicInspectionListDao, BasicInspectionListEntity, BasicInspectionListDTO> implements BasicInspectionListService {
    @Autowired
    private BasicInspectionListDao basicInspectionListDao;
    @Override
    public QueryWrapper<BasicInspectionListEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<BasicInspectionListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
    /**
     * 查询抽查样本前一次的图片附件
     */
    public String getBeforeAccessory(Long companyId,Long inspectionId, Date now){
        String beforeAccessory= basicInspectionListDao.getBeforeAccessory(companyId,inspectionId,now);
        return beforeAccessory;
    }
    /**
     * 根据抽查项id查询抽查项周期
     */
    public  Long getCycleByInspectionId(Long inspectionId){
        Long  cycle= basicInspectionListDao.getCycleByInspectionId(inspectionId);
        return cycle;
    }
}