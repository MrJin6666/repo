package com.goboosoft.modules.basicmeasure.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.basicmeasure.entity.BasicMeasureEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计量单位
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-12
 */
@Mapper
public interface BasicMeasureDao extends BaseDao<BasicMeasureEntity> {
	
}