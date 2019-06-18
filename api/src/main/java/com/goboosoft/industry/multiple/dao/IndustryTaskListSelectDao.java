package com.goboosoft.industry.multiple.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.dto.IndustryTaskListSelectDTO;
import com.goboosoft.industry.multiple.entity.IndustryTaskListSelectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Mapper
public interface IndustryTaskListSelectDao extends BaseDao<IndustryTaskListSelectEntity> {
    /**
     * 行业新任务发布列表查询
     * @return
     */
    List<IndustryTaskListSelectDTO> getSendList(@Param("params") Map<String, Object> params);
    /**
     * 行业新任务接收列表查询
     * @return
     */
    List<IndustryTaskListSelectDTO> getReceiveList(@Param("params") Map<String, Object> params);
    /**
     * 行业新任务待确认列表查询
     * @return
     */
    List<IndustryTaskListSelectDTO> getBeConfirmedList(@Param("params") Map<String, Object> params);
    /**
     * 查询任务待处理超期已完成列表
     * @return
     */
    List<IndustryTaskListSelectDTO> getHomePageList(@Param("params") Map<String, Object> params);
}