package com.goboosoft.industry.multiple.dao;


import com.goboosoft.common.dao.BaseDao;
import com.goboosoft.industry.multiple.entity.IndustryTaskListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
@Mapper
public interface IndustryTaskListDao extends BaseDao<IndustryTaskListEntity> {
    /**
     * 分页查询新任务列表
     */
    List<IndustryTaskListEntity> taskPageList();
    /**
     * 查询已存在的最新订单号
     */
    String orderNumberSelect();
   }