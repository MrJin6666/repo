package com.goboosoft.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description:
 *
 * @author cy
 * @date 2019年03月08日 14:56
 * version 1.0
 */
public class GenerateOrderUtil {

    /**
     * 生成订单号
     *
     * @return
     */
    public static String doOrderNum() {
        Random random = new Random();
        SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
        String subjectno = allTime.format(new Date())+random.nextInt(10)+random.nextInt(10);
        return subjectno+random.nextInt(10);
    }

    public static void main(String[] args) {
        System.out.println(GenerateOrderUtil.doOrderNum());
    }

}
