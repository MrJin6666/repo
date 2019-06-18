package com.goboosoft.modules.sysnotice.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.CrudService;
import com.goboosoft.modules.sysnotice.dto.SysNoticeDTO;
import com.goboosoft.modules.sysnotice.entity.SysNoticeEntity;

import java.util.Map;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-11
 */
public interface SysNoticeService extends CrudService<SysNoticeEntity, SysNoticeDTO> {

    PageData<SysNoticeDTO> pageList(Map<String, Object> params);
}