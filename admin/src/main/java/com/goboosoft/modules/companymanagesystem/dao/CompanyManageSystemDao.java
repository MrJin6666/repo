package com.goboosoft.modules.companymanagesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.companymanagesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.modules.companymanagesystem.entity.CompanyManageSystemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
 */
@Mapper
public interface CompanyManageSystemDao extends BaseDao<CompanyManageSystemEntity> {

    IPage<CompanyManageSystemDTO> findPageList(IPage page,@Param("params") Map<String, Object> params);
}