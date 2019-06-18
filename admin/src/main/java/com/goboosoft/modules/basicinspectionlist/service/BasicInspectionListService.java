package com.goboosoft.modules.basicinspectionlist.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.basicinspectionlist.entity.BasicInspectionListEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
public interface BasicInspectionListService extends CrudService<BasicInspectionListEntity, BasicInspectionListDTO> {

    PageData<BasicInspectionListDTO> pageList(Map<String, Object> params);

    void deleteByPid(Long pid);

    List<BasicInspectionListDTO> findConmpanyInspectList(Long comId);

    List<BasicInspectionListDTO> companyList(Map<String, Object> params);

    void saveDTO(BasicInspectionListDTO dto);

    void updateDTO(BasicInspectionListDTO dto);

    void deleteByIds(Long[] ids);
}