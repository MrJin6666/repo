package com.goboosoft.company.companyinspection.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.company.companyinspection.dto.ConmpanyInspectDTO;
import com.goboosoft.company.companyinspection.entity.ConmpanyInspectEntity;
import com.goboosoft.industry.homepage.dto.InspectDetailsDTO;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;

import java.util.List;
import java.util.Map;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-06
 */
public interface ConmpanyInspectService extends CrudService<ConmpanyInspectEntity, ConmpanyInspectDTO> {

    List<ConmpanyInspectEntity> selectConmpanyInspectList(QueryWrapper<ConmpanyInspectEntity> wrapper);

    List<ConmpanyInspectDTO> getListNameAndNum(Long userId);

    Long saveCompany(SysCompanyDTO sysCompanyDTO,Long userId);

    Integer getCount(Long conmpanyInspectId,Long companyId,Long userId);

    String getRoomId();
    /**
     * 根据企业id和检查项id查询检查项个数
     */
    List<ConmpanyInspectDTO> getInspectCountById(Long companyId ,Long listId);
    /**
     * 检查项分组总数统计
     */
     List<ConmpanyInspectDTO> getInspectCountByGroup(Long userId);
    /**
     * 根据检查项id获取检查项统计详情列表
     */
    List<InspectDetailsDTO> getInspectCountDetails(Map<String,Object> params);

    Integer getByName(String name);

    /**
     * 判断此检查项名称是否已存在
     * @param dto
     * @return
     */
    Integer isExits(ConmpanyInspectDTO dto);

    /**
     * 生成治理计划
     */
    void generateGovern(List<BasicInspectionListDTO> list, Long companyId);
}