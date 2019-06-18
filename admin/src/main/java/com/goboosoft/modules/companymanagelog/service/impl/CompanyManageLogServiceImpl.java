package com.goboosoft.modules.companymanagelog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.companymanagelog.dao.CompanyManageLogDao;
import com.goboosoft.modules.companymanagelog.dto.CompanyManageLogDTO;
import com.goboosoft.modules.companymanagelog.entity.CompanyManageLogEntity;
import com.goboosoft.modules.companymanagelog.service.CompanyManageLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 企业管理日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Service
public class CompanyManageLogServiceImpl extends CrudServiceImpl<CompanyManageLogDao, CompanyManageLogEntity, CompanyManageLogDTO> implements CompanyManageLogService {

    @Override
    public QueryWrapper<CompanyManageLogEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CompanyManageLogEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<CompanyManageLogDTO> findByComId(Long id) {
        return baseDao.findByComId(id);
    }

    /**
     * 通过公司id查询
     * @param companyId
     * @return
     */
    @Override
    public CompanyManageLogDTO findByCompanyId(Long companyId) {
        return baseDao.findByCompanyId(companyId);
    }
}