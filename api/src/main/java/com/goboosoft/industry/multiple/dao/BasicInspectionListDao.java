package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.dto.BasicInspectionListDTO;
import com.goboosoft.industry.multiple.entity.BasicInspectionListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 检查项/检查内容信息表
 * created by yangkun
 * created time 2019/2/14
 * class describe
 */
@Mapper
@Repository("inspectionListDao")
public interface BasicInspectionListDao extends BaseDao<BasicInspectionListEntity> {
    /**
     * 查询抽查样本前一次的图片附件
     */
    String getBeforeAccessory(@Param("companyId") Long companyId,@Param("inspectionId")Long inspectionId,@Param("now") Date now);
    /**
     * 根据抽查项id查询抽查项周期
     */
    Long getCycleByInspectionId(@Param("inspectionListId")Long inspectionId);

    BasicInspectionListDTO getPid(@Param("inspectionListId") Long inspectionListId);

    List<BasicInspectionListDTO> getListPid(@Param("pid") String pid);

    List<BasicInspectionListDTO> findConmpanyInspectList(Long comId);
}
