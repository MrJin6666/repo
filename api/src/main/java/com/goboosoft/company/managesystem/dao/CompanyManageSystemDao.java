package com.goboosoft.company.managesystem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.company.managesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-08
 */
@Mapper
@Repository("companyManageSystemDao")
public interface CompanyManageSystemDao extends BaseDao<CompanyManageSystemEntity> {

    CompanyManageSystemEntity getNoticeItemById(Long id);

    List<CompanyManageSystemDTO> getSysCompanyInfo(@Param("params")Map<String, Object> params);

    void  deleteNoticeItem(@Param("id") Long id);

    IPage<CompanyManageSystemDTO> pageList(IPage page,@Param("params")  Map<String, Object> params);

    List<CompanyManageSystemEntity> deptNoticeList(@Param("params")  CompanyManageSystemEntity params);

    List<CompanyManageSystemEntity> getDeptIdByCompanyId(@Param("companyId") Long companyId);

    CompanyManageSystemDTO getDetail(@Param("id") Long id);

    List<CompanyManageSystemDTO> getIndustryInfo(@Param("params")Map<String, Object> params);
}