package com.goboosoft.modules.conmpanyinspect.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.conmpanyinspect.dto.ConmpanyInspectDTO;
import com.goboosoft.modules.conmpanyinspect.entity.ConmpanyInspectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业检查项
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Mapper
public interface ConmpanyInspectDao extends BaseDao<ConmpanyInspectEntity> {

    List<ConmpanyInspectDTO> findConmpanyInspectBycomId(Long companyId);

    IPage<Map<String, Object>> screenCompanyInspectionNum(IPage page,@Param("params") Map<String, Object> params);
}