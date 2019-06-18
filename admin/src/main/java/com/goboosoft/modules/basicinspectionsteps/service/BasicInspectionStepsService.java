package com.goboosoft.modules.basicinspectionsteps.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import com.goboosoft.modules.basicinspectionsteps.entity.BasicInspectionStepsEntity;

import java.util.List;

/**
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
public interface BasicInspectionStepsService extends CrudService<BasicInspectionStepsEntity, BasicInspectionStepsDTO> {

    void deleteByInspectionListId(Long id);

    List<BasicInspectionStepsDTO> getByInspectionListId(Long id);
}