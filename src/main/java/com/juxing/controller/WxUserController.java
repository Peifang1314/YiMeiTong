package com.juxing.controller;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.util.AdvancedUtil;
import com.juxing.common.util.CommonUtil;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.pojo.reqPojo.SearchRequest;
import com.juxing.pojo.wechatPojo.*;
import com.juxing.service.MyTokenService;
import com.juxing.service.UserService;
import com.juxing.service.WechatQRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/1 17
 * @Description: 调用微信接口获得授权，得到用户的微信数据存到数据库
 */

@RestController
@RequestMapping("/api")
public class WxUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyTokenService myTokenService;

    @Autowired
    private WechatQRcodeService wechatQRcodeService;


    @RequestMapping("/getUserInfo")
    public RespObj getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        String state = request.getParameter("state");
        System.out.println("state:" + state);

        // 用户同意授权
        // 获取网页授权access_token
        WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(App.APPID, App.APPSECRET, code);
        if (null == weixinOauth2Token) {
            return new RespObj(800, "error", 0, null);
        } else {
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            UserInfo userInfo = AdvancedUtil.getUserInfo(accessToken, openId);
            //查询用户是否已注册过，判断用户状态
            User user = userService.getUser(userInfo.getUserOpenid());

            if (null == user) {
                //用户第一次进入，先保存用户的微信数据
                return userService.saveUserinfo(userInfo);
            } else {
                //已注册，判断用户状态 审核中或已通过
                int status = user.getUserStatus();
                if (status == 1) {
                    //用户审核中
                    return new RespObj(200, "1--正在审核", 1, userService.updateUser(userInfo));
                } else if (status == 2) {
                    //用户状态正常，更新用户的数据，并返回用户数据
                    return new RespObj(200, "2--正常状态", 2, userService.updateUser(userInfo));
                } else {
                    return new RespObj(200, "3--审核失败/失效", 3, userService.updateUser(userInfo));
                }
            }
        }
    }

    /**
     * 获得用户的审核状态
     *
     * @param request
     * @return
     */
    @RequestMapping("/getUserStatus")
    public RespObj getUserStatus(@RequestBody SearchRequest request) {
        String openId = request.getText();
        User user = userService.getUser(openId);
        if (Objects.equals(null, user)) {
            return RespObj.error();
        } else {
            return new RespObj(200, "success", 1, user.getUserStatus());
        }
    }

    /**
     * 创建永久带参二维码
     *
     * @param request
     * @return
     */
    @RequestMapping("/getForeverQRcode")
    public RespObj getForeverQRcode(@RequestBody SearchRequest request) {
        String openId = request.getText();
        MyToken token = (MyToken) myTokenService.getMyToken().getObj();
        String shortUrl = wechatQRcodeService.createForeverQRcode(token.getAccessToken(), openId);
        if (Objects.equals(null, shortUrl)) {
            return RespObj.error();
        } else {
            return new RespObj(200, "永久二维码生成", 1, shortUrl);
        }

    }


    /**
     * 创建临时带参二维码
     *
     * @param request
     * @return
     */
    @RequestMapping("/getTempQRcode")
    public RespObj getWechatPic(@RequestBody SearchRequest request) {
        //参数为用户的OpenID，形成上下级关系
        String datas = request.getText();
        //获取数据库内存储的accessToken值,防止token重复刷新达到使用上限（2000）
        MyToken myToken = (MyToken) myTokenService.getMyToken().getObj();


        //请求参数str（临时）
        JsonWechatPicReq wechatPicReq = new JsonWechatPicReq();
        //临时二维码的有效时间（30天）
        wechatPicReq.setExpire_seconds(2592000L);
        wechatPicReq.setAction_name("QR_STR_SCENE");
        wechatPicReq.setAction_info(new Action_info(new Scene(datas)));

        String post = JSONObject.toJSONString(wechatPicReq);

        Ticket ticket = AdvancedUtil.getTicket(myToken.getAccessToken(), post);
        String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        //临时二维码的图片地址，长链接
        requestUrl = requestUrl.replace("TICKET", ticket.getTicket());
        // 长链接转短链接

        JSONObject jsonObject = CommonUtil.long2short(myToken.getAccessToken(), requestUrl);
        // 短链接地址
        String shortUrl = jsonObject.getString("short_url");

        return new RespObj(200, "success", 1, shortUrl);
    }


}
