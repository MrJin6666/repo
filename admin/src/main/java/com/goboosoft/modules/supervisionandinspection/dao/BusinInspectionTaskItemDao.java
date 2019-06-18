package com.goboosoft.modules.supervisionandinspection.dao;

import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.supervisionandinspection.entity.BusinInspectionTaskItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 业务 - 督导检查任务项信息表
 *
 * @author yflong ${email}
 * @since 1.0.0 2019-03-14
 */
@Mapper
public interface BusinInspectionTaskItemDao extends BaseDao<BusinInspectionTaskItemEntity> {
	
}