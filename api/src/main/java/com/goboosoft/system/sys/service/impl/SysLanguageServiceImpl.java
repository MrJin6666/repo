/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.sys.service.impl;

import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.system.sys.dao.SysLanguageDao;
import com.goboosoft.system.sys.entity.SysLanguageEntity;
import com.goboosoft.system.sys.service.SysLanguageService;
import org.springframework.stereotype.Service;

/**
 * 国际化
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if(baseDao.getLanguage(entity) == null){
            baseDao.insert(entity);
        }else {
            baseDao.updateLanguage(entity);
        }
    }
}