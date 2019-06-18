package com.goboosoft.modules.companymanagesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.companymanagesystem.dao.CompanyManageSystemDao;
import com.goboosoft.modules.companymanagesystem.dto.CompanyManageSystemDTO;
import com.goboosoft.modules.companymanagesystem.entity.CompanyManageSystemEntity;
import com.goboosoft.modules.companymanagesystem.service.CompanyManageSystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 企业管理制度
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-13
 */
@Service
public class CompanyManageSystemServiceImpl extends CrudServiceImpl<CompanyManageSystemDao, CompanyManageSystemEntity, CompanyManageSystemDTO> implements CompanyManageSystemService {

    @Override
    public QueryWrapper<CompanyManageSystemEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CompanyManageSystemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<CompanyManageSystemDTO> pageList(Map<String, Object> params) {
        IPage<CompanyManageSystemDTO> pageList = baseDao.findPageList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<CompanyManageSystemDTO> objectPageData = new PageData<CompanyManageSystemDTO>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }
}