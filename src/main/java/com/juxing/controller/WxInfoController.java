package com.juxing.controller;

import com.juxing.common.util.SignUtil;
import com.juxing.common.util.WXPayUtils;
import com.juxing.common.util.WechatMessageUtil;
import com.juxing.common.vo.Resp;
import com.juxing.message.resp.TextMessage;
import com.juxing.pojo.mysqlPojo.Relations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    @ResponseBody
    public String doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        System.out.println("post请求进入");
//        PrintWriter out = response.getWriter();

        TextMessage textMessage = null;

        try {
            //解析微信的XML数据
            Map<String, String> map = WechatMessageUtil.xmlToMap(request);

            String toUserName = map.get("ToUserName");
            //当前用户的openid
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String picUrl = map.get("PicUrl");
            String content = map.get("Content");
            String event = map.get("Event");
            //携带的参数，上级的openid
            String eventKey = map.get("EventKey");
            String nickname = map.get("nick_name");
            System.out.println("携带参数：" + eventKey);

            // 普通消息
            if ("text".equals(msgType)) { // 文本消息
                // todo 处理文本消息
            } else if ("image".equals(msgType)) { // 图片消息
                // todo 处理图片消息
            } else if ("voice".equals(msgType)) { //语音消息
                // todo 处理语音消息
            } else if ("video".equals(msgType)) { // 视频消息
                // todo 处理视频消息
            } else if ("shortvideo".equals(msgType)) { // 小视频消息
                // todo 处理小视频消息
            } else if ("location".equals(msgType)) { // 地理位置消息
                // todo 处理地理位置消息
            } else if ("link".equals(msgType)) { // 链接消息
                // todo 处理链接消息
            } else if ("event".equals(msgType)) { // 事件消息
                // 区分事件推送
                if ("subscribe".equals(event)) {
                    // 订阅事件 或 未关注扫描二维码事件
                    //返回消息，text类型
                    textMessage = new TextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType("text");

                    //1、判断二维码来源（1--店家、2--渠道负责人）
                    //截取携带的参数 trueKey
                    String trueKey = eventKey.substring(8);
                    char a = trueKey.charAt(0);
                    String first = String.valueOf(a);
                    if (Objects.equals("1", first)) {
                        //来源是店家
                        Relations relation = new Relations();
                        //设置上下级关系
                        relation.setUserId(fromUserName);
                        relation.setFatherId(eventKey.substring(9));
                        //1、判断用户关系是否存在
                        Resp ret = relaService.getRelation(fromUserName);
                        //1.1 不存在，就存储
                        if (ret.getStatus() == 404) {
                            //存储新用户上下级关系
                            Resp resp = relaService.saveFather(relation);
                            if (resp.getStatus() == 800) {
                                textMessage.setContent("error");
                            } else {
                                textMessage.setContent("http://m.superstar.vc");
                            }
                        } else {
                            //1.2 存在就返回注册网址
                            textMessage.setContent("http://m.superstar.vc");
                        }
                    } else if (Objects.equals(2, first)) {
                        //来源是渠道，渠道关系形成
                        Relations relation = new Relations();
                        //设置渠道关系
                        relation.setUserId(fromUserName);
                        relation.setServiceId(eventKey.substring(1));

                        //1、判断关系是否存在
                        Resp ret = relaService.getRelation(fromUserName);
                        //1.1 用户关系不存在，就存储
                        if (ret.getStatus() == 404) {
                            //存储新用户的渠道关系
                            Resp resp = relaService.saveService(relation);
                            if (resp.getStatus() == 800) {
                                textMessage.setContent("error");
                            } else {
                                textMessage.setContent("http://m.superstar.vc");
                            }
                        } else {
                            //1.2 用户关系存在
                            //1.2.1 判断渠道关系是否存在
                            Resp ret2 = relaService.getSerRelation(fromUserName);
                            if (ret2.getStatus() == 404) {
                                // 渠道关系不存在，进行存储
                                Resp ret3 = relaService.updateSerRelation(relation);
                                if (ret3.getStatus() == 800) {
                                    textMessage.setContent("error");
                                } else {
                                    //渠道关系存储成功
                                    textMessage.setContent("http://m.superstar.vc");
                                }
                            } else {
                                //渠道关系存在
                                textMessage.setContent("http://m.superstar.vc");
                            }
                        }
                    }

                } else if ("unsubscribe".equals(event)) { // 取消订阅事件
                    // todo 处理取消订阅事件
                } else if ("SCAN".equals(event)) {
                    // 已关注扫描二维码事件
                    //返回消息，text类型
                    textMessage = new TextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType("text");

                    //1、判断二维码来源（1--店家、2--渠道负责人）
                    char a = eventKey.charAt(0);
                    String first = String.valueOf(a);
                    if (Objects.equals("1", first)) {
                        //来源是店家
                        Relations relation = new Relations();
                        //设置上下级关系
                        relation.setUserId(fromUserName);
                        relation.setFatherId(eventKey.substring(1));
                        //1、判断用户关系是否存在
                        Resp ret = relaService.getRelation(fromUserName);
                        //1.1 不存在，就存储
                        if (ret.getStatus() == 404) {
                            //存储新用户上下级关系
                            Resp resp = relaService.saveFather(relation);
                            if (resp.getStatus() == 800) {
                                textMessage.setContent("error");
                            } else {
                                textMessage.setContent("http://m.superstar.vc");
                            }
                        } else {
                            //1.2 存在就返回注册网址
                            textMessage.setContent("http://m.superstar.vc");
                        }
                    } else if (Objects.equals(2, first)) {
                        //来源是渠道，渠道关系形成
                        Relations relation = new Relations();
                        //设置渠道关系
                        relation.setUserId(fromUserName);
                        relation.setServiceId(eventKey.substring(1));

                        //1、判断关系是否存在
                        Resp ret = relaService.getRelation(fromUserName);
                        //1.1 用户关系不存在，就存储
                        if (ret.getStatus() == 404) {
                            //存储新用户的渠道关系
                            Resp resp = relaService.saveService(relation);
                            if (resp.getStatus() == 800) {
                                textMessage.setContent("error");
                            } else {
                                textMessage.setContent("http://m.superstar.vc");
                            }
                        } else {
                            //1.2 用户关系存在
                            //1.2.1 判断渠道关系是否存在
                            Resp ret2 = relaService.getSerRelation(fromUserName);
                            if (ret2.getStatus() == 404) {
                                // 渠道关系不存在，进行存储
                                Resp ret3 = relaService.updateSerRelation(relation);
                                if (ret3.getStatus() == 800) {
                                    textMessage.setContent("error");
                                } else {
                                    //渠道关系存储成功
                                    textMessage.setContent("http://m.superstar.vc");
                                }
                            } else {
                                //渠道关系存在
                                textMessage.setContent("http://m.superstar.vc");
                            }
                        }

                    }
                } else if ("LOCATION".equals(event)) { // 上报地理位置事件
                    // todo 处理上报地理位置事件
                } else if ("CLICK".equals(event)) { // 点击菜单拉取消息时的事件推送事件
                    // todo 处理点击菜单拉取消息时的事件推送事件
                } else if ("VIEW".equals(event)) { // 点击菜单跳转链接时的事件推送
                    // todo 处理点击菜单跳转链接时的事件推送
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
//            out.close();
        }
        return WechatMessageUtil.textMessageToXml(textMessage);
    }


}
