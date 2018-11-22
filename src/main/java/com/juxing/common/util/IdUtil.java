package com.juxing.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 10
 * @Description: 生成用户ID，日期+6位随机数
 */


public class IdUtil {


    public static String getId() {
        String id = "";

        //获取当前时间戳
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHH");
        String temp = sf.format(new Date());

        //获取6位随机数
        int random = (int) ((Math.random() + 1) * 1000);
        id = "JX" + temp + random;
        return id;
    }

    /**
     * 时间戳转字符串
     *
     * @param timeStamp
     * @return
     */
    public static String time2String(long timeStamp) {
        //long timeStamp = 1495777335060;//直接是时间戳
//        long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//这个是你要转成后的时间的格式
        String time = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
        return time;
    }


}
