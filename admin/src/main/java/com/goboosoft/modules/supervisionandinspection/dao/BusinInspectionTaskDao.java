package com.goboosoft.modules.supervisionandinspection.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.modules.supervisionandinspection.entity.BusinInspectionTaskEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 业务 - 检查任务信息表
 *
 * @author DMXUAN
 * @since 1.0.0 2019-03-14
 */
@Mapper
public interface BusinInspectionTaskDao extends BaseDao<BusinInspectionTaskEntity> {

    IPage<Map<String, Object>> screenDd(IPage<BusinInspectionTaskEntity> page, Map<String, Object> params);
}