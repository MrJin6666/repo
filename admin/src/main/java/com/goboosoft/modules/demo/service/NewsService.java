/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.modules.demo.service;

import com.goboosoft.common.service.BaseService;
import com.goboosoft.modules.demo.entity.NewsEntity;
import com.goboosoft.common.page.PageData;
import com.goboosoft.modules.demo.dto.NewsDTO;

import java.util.Map;

/**
 * 新闻
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface NewsService extends BaseService<NewsEntity> {

    PageData<NewsDTO> page(Map<String, Object> params);

    NewsDTO get(Long id);

    void save(NewsDTO dto);

    void update(NewsDTO dto);
}

