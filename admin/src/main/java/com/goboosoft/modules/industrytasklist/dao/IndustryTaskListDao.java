package com.goboosoft.modules.industrytasklist.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.industrytasklist.dto.IndustryTaskListDTO;
import com.goboosoft.modules.industrytasklist.entity.IndustryTaskListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 行业 - 新任务发布表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Mapper
public interface IndustryTaskListDao extends BaseDao<IndustryTaskListEntity> {

    IPage<IndustryTaskListDTO> findPageList(IPage page, @Param("params") Map<String, Object> params);
}