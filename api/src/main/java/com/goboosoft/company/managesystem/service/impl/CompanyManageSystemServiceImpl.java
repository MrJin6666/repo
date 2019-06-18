package com.goboosoft.company.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.managesystem.dao.CompanyManageSystemDao;
import com.goboosoft.company.managesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.company.managesystem.entity.CompanyManageSystemEntity;
import com.goboosoft.company.managesystem.service.CompanyManageSystemService;
import com.goboosoft.industry.multiple.dto.SysCompanyDTO;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-08
 */
@Service
public class CompanyManageSystemServiceImpl extends CrudServiceImpl<CompanyManageSystemDao, CompanyManageSystemEntity, CompanyManageSystemDTO> implements CompanyManageSystemService {

    @Autowired
    private SysUserService sysUserService;
    @Override
    public QueryWrapper<CompanyManageSystemEntity> getWrapper(Map<String, Object> params){
        Integer type = Integer.valueOf(params.get("type").toString());
        QueryWrapper<CompanyManageSystemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type);
        wrapper.orderByDesc("create_date");
        return wrapper;
    }

    @Override
    public PageData<CompanyManageSystemDTO> pageLists(Map<String, Object> params) {
        IPage<CompanyManageSystemDTO> pageList = baseDao.pageList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<CompanyManageSystemDTO> objectPageData = new PageData<CompanyManageSystemDTO>(pageList.getRecords(), pageList.getTotal());
        List<CompanyManageSystemDTO> list = objectPageData.getList();
        for(CompanyManageSystemDTO companyManageSystemDTO : list){
            companyManageSystemDTO.setName(sysUserService.get(companyManageSystemDTO.getCreator()).getRealName());
        }
        return objectPageData;
    }


}