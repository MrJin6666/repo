package com.goboosoft.modules.basicinspectionlist.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.common.page.PageData;
import com.goboosoft.modules.basicinspectionlist.dto.BasicInspectionListDTO;
import com.goboosoft.modules.basicinspectionlist.entity.BasicInspectionListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 基础 - 检查项/检查内容信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Mapper
public interface BasicInspectionListDao extends BaseDao<BasicInspectionListEntity> {

    IPage<BasicInspectionListDTO> findPageList(IPage page,@Param("params") Map<String, Object> params);

    List<BasicInspectionListDTO> findAllByPid(@Param("pid") Long pid,@Param("level") String level);

    void deleteByPid(@Param("pid") Long pid);

    List<BasicInspectionListDTO> findConmpanyInspectList(@Param("comId") Long comId);

    List<BasicInspectionListDTO> companyList(@Param("params") Map<String, Object> params);
}