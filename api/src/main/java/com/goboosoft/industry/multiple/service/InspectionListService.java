package com.goboosoft.industry.multiple.service;

import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.entity.BasicInspectionListEntity;

import java.util.List;

/**
 * 检查项/检查内容信息管理
 * created by yangkun
 * created time 2019/2/14
 * class describe
 */
public interface InspectionListService extends CrudService<BasicInspectionListEntity, BasicInspectionListDTO> {

    /**
     * 查询 - 通过级别代码和上级主键
     * @param level
     * @param pid
     * @return
     */
    List<BasicInspectionListEntity> getEntityListByLevelAndPid(String level, Long pid);

    List<BasicInspectionListDTO> getListName(Long companyId,Long userId);

    List<BasicInspectionListDTO> getListByPid(String pid);

    List<BasicInspectionListDTO> findConmpanyInspectList(Long companyId);
}
