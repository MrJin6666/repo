package com.goboosoft.modules.sysnotice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.CrudServiceImpl;
import com.goboosoft.modules.sysnotice.dao.SysNoticeDao;
import com.goboosoft.modules.sysnotice.dto.SysNoticeDTO;
import com.goboosoft.modules.sysnotice.entity.SysNoticeEntity;
import com.goboosoft.modules.sysnotice.service.SysNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-11
 */
@Service
public class SysNoticeServiceImpl extends CrudServiceImpl<SysNoticeDao, SysNoticeEntity, SysNoticeDTO> implements SysNoticeService {

    @Override
    public QueryWrapper<SysNoticeEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SysNoticeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<SysNoticeDTO> pageList(Map<String, Object> params) {
        IPage<SysNoticeDTO> pageList = baseDao.findPageList(getPage(params, Constant.CREATE_DATE, false),params);
        PageData<SysNoticeDTO> objectPageData = new PageData<SysNoticeDTO>(pageList.getRecords(), pageList.getTotal());
        return objectPageData;
    }
}