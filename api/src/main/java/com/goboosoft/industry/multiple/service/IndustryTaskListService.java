package com.goboosoft.industry.multiple.service;


import com.goboosoft.common.service.CrudService;
import com.goboosoft.industry.multiple.dto.IndustryTaskListDTO;
import com.goboosoft.industry.multiple.entity.IndustryTaskListEntity;

/**
 * 行业 - 新任务发布表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2019-03-05
 */
public interface IndustryTaskListService extends CrudService<IndustryTaskListEntity, IndustryTaskListDTO> {
    /**
     * 行业新任务创建
     * @param industryTaskListDTO
     */
    //void insertNewTask(IndustryTaskListDTO industryTaskListDTO);

    /**
     * 查询已存在的最新订单号
     */
     String orderNumberSelect();

}