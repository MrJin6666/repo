package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.entity.BasicInspectionListEntity;

import java.util.Date;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-11
 */
public interface BasicInspectionListService extends CrudService<BasicInspectionListEntity, BasicInspectionListDTO> {
    /**
     * 查询抽查样本前一次的图片附件
     */
    String getBeforeAccessory(Long companyId,Long inspectionId, Date now);
    /**
     * 根据抽查项id查询抽查项周期
     */
    Long getCycleByInspectionId(Long inspectionId);
}
