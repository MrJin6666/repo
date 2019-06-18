/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.system.oss.cloud;

import com.goboosoft.common.constant.Constant;
import com.goboosoft.common.utils.SpringContextUtils;
import com.goboosoft.system.sys.service.SysParamsService;

/**
 * 文件上传Factory
 * @author Mark sunlightcs@gmail.com
 */
public final class OSSFactory {
    private static SysParamsService sysParamsService;

    static {
        OSSFactory.sysParamsService = SpringContextUtils.getBean(SysParamsService.class);
    }

    public static com.goboosoft.system.oss.cloud.AbstractCloudStorageService build(){
        //获取云存储配置信息
        com.goboosoft.system.oss.cloud.CloudStorageConfig config = sysParamsService.getValueObject(Constant.CLOUD_STORAGE_CONFIG_KEY, com.goboosoft.system.oss.cloud.CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new com.goboosoft.system.oss.cloud.QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new com.goboosoft.system.oss.cloud.AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new com.goboosoft.system.oss.cloud.QcloudCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.FASTDFS.getValue()){
            return new com.goboosoft.system.oss.cloud.FastDFSCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.LOCAL.getValue()){
            return new com.goboosoft.system.oss.cloud.LocalCloudStorageService(config);
        }

        return null;
    }

}