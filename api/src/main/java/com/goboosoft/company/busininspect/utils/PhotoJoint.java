package com.goboosoft.company.busininspect.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 图片链接--拼接
 * YangFL
 */
@Component
public class PhotoJoint {

    @Value("${filePath}")
    private String filePath;

    public String addPhotoPath(String photos) {
        if (StringUtils.isEmpty(photos)){
            return null;
        }
        StringBuffer photoPaths = new StringBuffer();
        String[] split = photos.split(",");
        for (String photo : split) {
            photoPaths.append(",").append(filePath).append(photo);
        }
        return photoPaths.substring(1);//跳过第一个","
    }


}
