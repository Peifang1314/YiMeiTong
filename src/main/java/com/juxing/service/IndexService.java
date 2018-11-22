package com.juxing.service;

import com.juxing.common.vo.RespObj;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/13 10
 * @Description: 首页的service
 */
public interface IndexService {

    /**
     *
     * @param openid 用户的openid
     * @return 根据用户的openid返回首页的数据（主页）
     */
    RespObj getIndex(String openid);

    /**
     *
     * @param openid
     * @return 根据用户的openid返回我的模块信息(最后一个模块)
     */
    RespObj getMyInfo(String openid);


}
