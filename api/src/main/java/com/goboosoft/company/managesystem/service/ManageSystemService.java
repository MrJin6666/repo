package com.goboosoft.company.managesystem.service;


import com.goboosoft.common.service.BaseService;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.managesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.company.managesystem.dto.UploadingNoticeDTO;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManageSystemService extends
        CrudService<CompanyManageSystemEntity, CompanyManageSystemDTO> {

   void uploadingAttachment(CompanyManageSystemDTO companyManageSystemDTO);

   /**
    * 行业制度管理
    * @return
    */
   List<CompanyManageSystemDTO> getIndustryInfo(Map<String, Object> params) ;
   List<CompanyManageSystemDTO> getSysCompanyInfo(Map<String, Object> params);

   void deleteNoticeItem(Long id);

   List<CompanyManageSystemEntity> deptNoticeList(CompanyManageSystemEntity params);

   List<CompanyManageSystemEntity> getDeptIdByCompanyId(Long companyId);

   CompanyManageSystemDTO getDetail(Long id);
}
