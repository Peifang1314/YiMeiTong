package com.juxing.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/21 17
 * @Description:
 */
public interface WechatCoreService {

    /**
     * 微信公众平台--处理request请求
     * @param request
     * @return
     */
    String processRequest(HttpServletRequest request);

}
