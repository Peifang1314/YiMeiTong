package com.juxing.common.util;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2018/10/25 10
 * @Description:
 */


public class IdUtil {


    public static String getId() {
        String id = "";

        //获取当前时间戳
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHH");
        String temp = sf.format(new Date());

        //获取6位随机数
        int random = (int) ((Math.random() + 1) * 100000);
        id = temp + random;
        return id;
    }

}
