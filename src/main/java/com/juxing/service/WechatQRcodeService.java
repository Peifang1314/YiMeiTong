package com.juxing.service;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/22 17
 * @Description:
 */
public interface WechatQRcodeService {

    /**
     * 创建永久二维码
     * @param access_Token 接口调用凭证
     * @param scene_str 参数（用户的openID）
     * @return
     */
    String createForeverQRcode(String access_Token,String scene_str);
}
