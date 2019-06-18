/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */
package com.goboosoft.system.message.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goboosoft.common.constant.Constant;

import com.goboosoft.common.exception.ErrorCode;
import com.goboosoft.common.exception.RenException;
import com.goboosoft.common.page.PageData;
import com.goboosoft.common.service.impl.BaseServiceImpl;
import com.goboosoft.system.message.dao.SysSmsDao;
import com.goboosoft.system.message.dto.SysSmsDTO;
import com.goboosoft.system.message.entity.SysSmsEntity;
import com.goboosoft.system.message.service.SysSmsService;
import com.goboosoft.system.message.sms.AbstractSmsService;
import com.goboosoft.system.message.sms.SmsFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SysSmsServiceImpl extends BaseServiceImpl<SysSmsDao, SysSmsEntity> implements SysSmsService {

    @Override
    public PageData<SysSmsDTO> page(Map<String, Object> params) {
        IPage<SysSmsEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );
        return getPageData(page, SysSmsDTO.class);
    }

    private QueryWrapper<SysSmsEntity> getWrapper(Map<String, Object> params){
        String mobile = (String)params.get("mobile");
        String status = (String)params.get("status");

        QueryWrapper<SysSmsEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(mobile), "mobile", mobile);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);

        return wrapper;
    }

    @Override
    public void send(String mobile,LinkedHashMap<String,String> map,String signName,String template) {
        /*LinkedHashMap<String, String> map;
        try {
            map = JSON.parseObject(params, LinkedHashMap.class);
        }catch (Exception e){
            throw new RenException(ErrorCode.JSON_FORMAT_ERROR);
        }*/

        //短信服务
        AbstractSmsService service = SmsFactory.build();
        if(service == null){
            throw new RenException(ErrorCode.SMS_CONFIG);
        }

        //发送短信
        service.sendSms(mobile, map,signName,template);
    }

    @Override
    public void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status) {
        SysSmsEntity sms = new SysSmsEntity();
        sms.setPlatform(platform);
        sms.setMobile(mobile);

        //设置短信参数
        if(MapUtil.isNotEmpty(params)){
            int index = 1;
            for(String value : params.values()){
                if(index == 1){
                    sms.setParams1(value);
                }else if(index == 2){
                    sms.setParams2(value);
                }else if(index == 3){
                    sms.setParams3(value);
                }else if(index == 4){
                    sms.setParams4(value);
                }
                index++;
            }
        }

        sms.setStatus(status);

        this.insert(sms);
    }
}
