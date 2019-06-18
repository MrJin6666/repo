package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.dto.SpotCheckCompanyDTO;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 公司
 * created by yangkun
 * created time 2019/2/11
 * class describe
 */
@Mapper
public interface SysCompanyDao extends BaseDao<SysCompanyEntity> {
    /**
     * 根据行业监管公司id查询下面所有被监管的公司
     */
    List<SpotCheckCompanyDTO> getCompanys(@Param("params") Map<String,Object> params);

    SysCompanyDTO getByCompanyId(Long companyId);
}
