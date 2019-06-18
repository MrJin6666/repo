package com.goboosoft.modules.sysnotice.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.sysnotice.dto.SysNoticeDTO;
import com.goboosoft.modules.sysnotice.entity.SysNoticeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-11
 */
@Mapper
public interface SysNoticeDao extends BaseDao<SysNoticeEntity> {

    IPage<SysNoticeDTO> findPageList(IPage page,@Param("params") Map<String, Object> params);
}