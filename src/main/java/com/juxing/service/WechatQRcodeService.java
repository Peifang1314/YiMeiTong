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
     * @return 带参数二维码的短链接
     */
    String createForeverQRcode(String access_Token,String scene_str);

    /**
     * 创建临时二维码
     * @param accessToken 接口调用凭证
     * @param sceneStr 参数（用户的openID）
     * @return 带参数二维码的短链接
     */
    String createTempQRcode(String accessToken,String sceneStr);


}
