/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.service;

import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.BaseService;
import com.goboosoft.system.sys.dto.SysDictDTO;
import com.goboosoft.system.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysDictService extends BaseService<SysDictEntity> {

    PageData<SysDictDTO> page(Map<String, Object> params);

    List<SysDictDTO> list(Map<String, Object> params);

    SysDictDTO get(Long id);

    void save(SysDictDTO dto);

    void update(SysDictDTO dto);

    void delete(Long[] ids);


    /**
     * 查询 - 通过字典类型
     * @param dictType
     * @return
     */
    List<SysDictEntity> getEntityByDictType(String dictType);

    /**
     * 查询 - 通过字典类型和父级主键
     * @param dictType
     * @param pid
     * @return
     */
    List<SysDictEntity> getEntityByDictTypeAndPid(String dictType, Long pid);

}