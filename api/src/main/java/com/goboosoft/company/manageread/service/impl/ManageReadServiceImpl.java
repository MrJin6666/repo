package com.goboosoft.company.manageread.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.company.manageread.dao.ManageReadDao;
import com.goboosoft.company.manageread.dto.ManageReadDTO;
import com.goboosoft.company.manageread.entity.ManageReadEntity;
import com.goboosoft.company.manageread.service.ManageReadService;
import com.goboosoft.industry.multiple.service.SysCompanyService;
import com.goboosoft.system.sys.dto.SysUserDTO;
import com.goboosoft.system.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-20
 */
@Service
public class ManageReadServiceImpl extends CrudServiceImpl<ManageReadDao, ManageReadEntity, ManageReadDTO> implements ManageReadService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Override
    public QueryWrapper<ManageReadEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ManageReadEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public List<ManageReadDTO> getLists(Long id, Integer isCompany){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("isCompany",isCompany);
        return baseDao.getLists(map);
    }

    @Override
    public List<ManageReadDTO> selectLists(Long id, Long manageId){
        return baseDao.selectLists(id,manageId);
    }

    @Override
    public Integer deleteManageRead(Long id, Integer isCompany, Long userId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("isCompany",isCompany);
        map.put("userId",userId);
        return baseDao.deleteManageRead(map);
    }
}