package com.goboosoft.modules.syscompany.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.syscompany.dto.SysCompanyDTO;
import com.goboosoft.modules.syscompany.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 基础 - 公司信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Mapper
public interface SysCompanyDao extends BaseDao<SysCompanyEntity> {

    IPage<SysCompanyDTO> findPageList(IPage page,@Param("params") Map<String, Object> params);

    IPage<Map<String,Object>> pageScreenList(IPage page,@Param("params") Map<String, Object> params);

    SysCompanyDTO findInspectionNum(@Param("companyId") Long companyId);
}