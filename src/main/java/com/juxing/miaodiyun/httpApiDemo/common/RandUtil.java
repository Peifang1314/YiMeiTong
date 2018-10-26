package com.juxing.miaodiyun.httpApiDemo.common;

import java.util.Random;

/***
 *生成验证码
 *
 */
public class RandUtil {

    public static int getRandNum() {


        //获取6位随机数
        int random = (int) ((Math.random() + 1) * 100000);

        return random;
    }

}
