package com.juxing.service;

import com.juxing.common.vo.RespObj;
import com.juxing.pojo.wechatPojo.MyToken;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/9 17
 * @Description: 获取access_token
 */
public interface MyTokenService {

    /**
     * 先获取token，判断是否过期，获取正确数据，已被定时方法取代
     * @return
     */
    RespObj getMyToken();

    /**
     * 定时任务获取access_token(30分钟1次)
     * @return 最新的access_Token
     */
    String getAccessToken();

}
