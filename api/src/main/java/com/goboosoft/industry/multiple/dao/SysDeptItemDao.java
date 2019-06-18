package com.goboosoft.industry.multiple.dao;

import com.goboosoft.industry.multiple.dto.SysDeptItemDTO;
import com.goboosoft.industry.multiple.dto.SysSameLevelDeptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门详情列表
 *
 * @author jinxin
 * created time 2019/3/6
 */
@Mapper
@Repository
public interface SysDeptItemDao {
    /**
     * 通过部门id查询部门详情列表
     * @param id
     * @return
     */
    List<SysDeptItemDTO> getSysDeptItemListBySysDeptId(Long id);

    /**
     * 根据部门id查询同级部门及下级部门列表(除去分公司)
     * @param id
     * @param pid
     * @return
     */
    List<SysSameLevelDeptDTO> getSysDeptAndSameLevelListBySysDeptId(@Param("id") Long id, @Param("pid") Long pid);
    /**
     * 根据部门id查询部门下的下一级列表(除去分公司)
     * @param id
     * @return
     */
    List<SysSameLevelDeptDTO> getSysDeptNextLevelListBySysDeptId(@Param("id") Long id);



}
