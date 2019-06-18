/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.message.dao;

import com.goboosoft.system.message.entity.SysSmsEntity;
import com.goboosoft.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysSmsDao extends BaseDao<SysSmsEntity> {
	
}
