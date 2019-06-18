package com.goboosoft.company.managesystem.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("manageSystemDao")
public interface ManageSystemDao extends BaseDao<CompanyManageSystemEntity> {

  List<CompanyManageSystemEntity> getSysCompanyInfo(Long creator, Integer isCompany);
}
