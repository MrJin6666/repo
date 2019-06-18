package com.goboosoft.company.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.BaseServiceImpl;

import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.common.utils.ConvertUtils;
import com.goboosoft.common.utils.Result;
import com.goboosoft.company.managesystem.dao.CompanyManageSystemDao;
import com.goboosoft.company.managesystem.dao.ManageSystemDao;
import com.goboosoft.company.managesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.company.managesystem.dto.UploadingNoticeDTO;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;
import com.goboosoft.company.managesystem.service.ManageSystemService;
import com.goboosoft.system.sys.dao.SysUserDao;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Service
public class ManageSystemServiceImpl extends CrudServiceImpl<CompanyManageSystemDao,
        CompanyManageSystemEntity,CompanyManageSystemDTO> implements ManageSystemService {

    @Autowired
    private ManageSystemDao manageSystemDao;
    @Autowired
    private CompanyManageSystemDao companyManageSystemDao;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public QueryWrapper<CompanyManageSystemEntity> getWrapper(Map<String, Object> params) {
        return null;
    }


    @Override
    public void uploadingAttachment(CompanyManageSystemDTO companyManageSystemDTO) {

//        baseDao.insert(companyManageSystemDTO);

    }

    @Override
    public List<CompanyManageSystemDTO> getSysCompanyInfo(Map<String, Object> params) {

        List<CompanyManageSystemDTO> sysCompanyInfo = companyManageSystemDao.getSysCompanyInfo(params);
        return sysCompanyInfo;
    }
    @Override
    public List<CompanyManageSystemDTO> getIndustryInfo(Map<String, Object> params){
        List<CompanyManageSystemDTO> sysCompanyInfo = companyManageSystemDao.getIndustryInfo(params);
        return sysCompanyInfo;
    }
    @Override
    public void deleteNoticeItem(Long id) {

//        Long id = 1104774730217951234L;
        CompanyManageSystemEntity noticeItems = companyManageSystemDao.getNoticeItemById(id);
        System.out.println("返回的公告实体： "+noticeItems);
        baseDao.deleteNoticeItem(id);
    }

    @Override
    public List<CompanyManageSystemEntity> deptNoticeList(CompanyManageSystemEntity params) {
        List<CompanyManageSystemEntity> systemEntities = baseDao.deptNoticeList(params);
        return systemEntities;
    }

    @Override
    public List<CompanyManageSystemEntity> getDeptIdByCompanyId(Long companyId) {
        List<CompanyManageSystemEntity> deptIdByCompanyId = baseDao.getDeptIdByCompanyId(companyId);
        return deptIdByCompanyId;
    }

    @Override
    public CompanyManageSystemDTO getDetail(Long id){
        return baseDao.getDetail(id);
    }

}
