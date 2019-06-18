package com.goboosoft.modules.basicinspectionsteps.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.basicinspectionsteps.dto.BasicInspectionStepsDTO;
import com.goboosoft.modules.basicinspectionsteps.entity.BasicInspectionStepsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 基础 - 检查项步骤信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
@Mapper
public interface BasicInspectionStepsDao extends BaseDao<BasicInspectionStepsEntity> {

    void deleteByInspectionListId(Long id);

    List<BasicInspectionStepsDTO> getByInspectionListId(Long id);
}