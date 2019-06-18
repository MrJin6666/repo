package com.goboosoft.modules.companymenuuser.dao;



import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.companymenuuser.dto.CompanyMenuDTO;
import com.goboosoft.modules.companymenuuser.entity.CompanyMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 企业菜单表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-08
 */
@Mapper
public interface CompanyMenuDao extends BaseDao<CompanyMenuEntity> {

    List<CompanyMenuDTO> selectAll();
}