package com.juxing.service;

import com.juxing.common.vo.RespObj;
import com.juxing.pojo.wechatPojo.MyToken;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/9 17
 * @Description: 获取access_token
 */
public interface MyTokenService {

    RespObj getMyToken();

}
