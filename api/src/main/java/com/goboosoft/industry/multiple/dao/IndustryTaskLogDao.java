package com.goboosoft.industry.multiple.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.entity.IndustryTaskLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业任务日志记录
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-07
 */
@Mapper
public interface IndustryTaskLogDao extends BaseDao<IndustryTaskLogEntity> {
	
}