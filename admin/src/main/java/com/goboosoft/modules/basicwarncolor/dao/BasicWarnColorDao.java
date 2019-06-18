package com.goboosoft.modules.basicwarncolor.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.basicwarncolor.entity.BasicWarnColorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 过期警告颜色
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Mapper
public interface BasicWarnColorDao extends BaseDao<BasicWarnColorEntity> {
	
}