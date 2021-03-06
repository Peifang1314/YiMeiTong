package com.juxing.service;

import com.juxing.common.vo.RespObj;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/14 15
 * @Description:
 */
public interface GetCodeService {

    /**
     * 向用户发送手机号
     * @param phone 发送的手机号码
     * @return
     */
    RespObj getAuthCode(String phone);
}
