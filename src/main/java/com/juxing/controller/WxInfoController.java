package com.juxing.controller;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.util.CommonUtil;
import com.juxing.common.util.SHA1;
import com.juxing.common.util.SignUtil;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.wechatPojo.App;
import com.juxing.pojo.wechatPojo.MyToken;
import com.juxing.service.MyTokenService;
import com.juxing.service.WechatCoreService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.juxing.service.RelaService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/13 21
 * @Description: 微信消息入口
 * 原文：https://blog.csdn.net/lyq8479/article/details/8944988
 */

@Controller
@RequestMapping("/weixin")
public class WxInfoController {

    @Autowired
    private RelaService relaService;

    @Autowired
    private WechatCoreService wechatService;

    @Autowired
    private MyTokenService myTokenService;


    /**
     * 确认请求来自微信服务器
     */
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        System.out.println("进入检查");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("ok");
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 接收微信的请求数据，对数据做出响应
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    @ResponseBody
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/xml;charset=utf-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        System.out.println("post请求进入");
        PrintWriter out = response.getWriter();

        String parseXml = wechatService.processRequest(request);
        out.print(parseXml);
        out.close();
    }

    @RequestMapping(value = "/jsApi",method = {RequestMethod.POST})
    @ResponseBody
    public String jsApi() {
        //1、获取access_token
        String accessToken = myTokenService.getAccessToken();
        //2、获取jsApiTicket
        Map ticketMap = CommonUtil.JsapiTicket(accessToken);
        String ticket = ticketMap.get("ticket").toString();
        //3、随即字符串、时间戳
        String noncestr = RandomStringUtils.randomAlphanumeric(12);
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        //4 获取url
        String url = "http://crm.jmzxyy.cn:6082/#/hotel";
        //5、将参数排序并拼接字符串
        String str = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;

        //6、将字符串进行sha1加密
        String signature = SHA1.encode(str);
        Map<String, String> map = new HashMap();
        map.put("timestamp", timestamp);
        map.put("accessToken", accessToken);
        map.put("ticket", ticket);
        map.put("noncestr", noncestr);
        map.put("signature", signature);
        map.put("appId",App.APPID);

        String respStr = JSONObject.toJSONString(map);

        return respStr;

    }


}
