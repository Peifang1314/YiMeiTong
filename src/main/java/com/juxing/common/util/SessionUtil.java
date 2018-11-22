package com.juxing.common.util;

import org.apache.commons.lang3.RandomStringUtils;


/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/6 14
 * @Description:随机生成一个16位的字符串
 */
public class SessionUtil {

    public static String getSession(){
        return RandomStringUtils.randomAlphanumeric(16);
    }
}
