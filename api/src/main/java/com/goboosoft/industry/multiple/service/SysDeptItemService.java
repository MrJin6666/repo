package com.goboosoft.industry.multiple.service;


import com.goboosoft.industry.multiple.dto.SysDeptItemDTO;
import com.goboosoft.industry.multiple.dto.SysSameLevelDeptDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门详情列表接口
 * @author jinxin
 * created time 2019/3/6
 */
public interface SysDeptItemService {
    /**
     * 根据部门id查询部门详情列表
     * @param id
     * @return
     */
    List<SysDeptItemDTO> getSysDeptItemListBySysDeptId(Long id);

    /**
     * 根据部门id查询同级部门及下级部门列表(除去分公司)
     * @param id
     * @return
     */
    List<SysSameLevelDeptDTO> getSysDeptAndSameLevelListBySysDeptId(Long id);

    /**
     * 根据部门id查询部门下的下一级列表(除去分公司)
     * @param id
     * @return
     */
    List<SysSameLevelDeptDTO> getSysDeptNextLevelListBySysDeptId(Long id);


    /**
     * 根据部门id查询部门下的下一级列表(除去分公司)
     * @param id
     * @return
     */

}
