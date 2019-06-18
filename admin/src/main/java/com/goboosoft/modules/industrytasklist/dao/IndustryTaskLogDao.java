package com.goboosoft.modules.industrytasklist.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.industrytasklist.entity.IndustryTaskLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业任务日志记录
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Mapper
public interface IndustryTaskLogDao extends BaseDao<IndustryTaskLogEntity> {
	
}