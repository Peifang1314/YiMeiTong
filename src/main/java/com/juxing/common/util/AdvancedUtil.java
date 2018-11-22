package com.juxing.common.util;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Code;
import com.juxing.pojo.wechatPojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/31 11
 * @Description:
 */
public class AdvancedUtil {
    private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

    /**
     * 获取网页授权凭证
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */


    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = new WeixinOauth2Token();
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

//        https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code

        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn((Integer) jsonObject.get("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
//                int errorCode = (int) jsonObject.get("errcode");
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return UserInfo 用户的微信数据
     */
    public static UserInfo getUserInfo(String accessToken, String openId) {
        UserInfo userInfo = null;

        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                userInfo = new UserInfo();
                // 用户的标识
                userInfo.setUserOpenid(jsonObject.getString("openid"));
                // 昵称
                userInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                userInfo.setSex((Integer) jsonObject.get("sex"));
                // 用户所在国家
                userInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                userInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                userInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
                // 用户特权信息
//                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
//                JSONArray.parseArray(jsonObject.getJSONArray("privilege").toString(),List.class);
            } catch (Exception e) {
                userInfo = null;
//                int errorCode = (int) jsonObject.get("errcode");
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return userInfo;
    }


    /**
     * 获取ticket，用来换取带参数二维码地址
     * @param token 令牌
     * @param outputStr 数据
     * @return
     */
    public static Ticket getTicket(String token, String outputStr) {
        Ticket ticket = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        requestUrl = requestUrl.replace("TOKEN", token);
        System.out.println("reqUrl:" + requestUrl);

        // 发起网页请求
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", outputStr);
        System.out.println("jsonObject:" + jsonObject);
        if (null != jsonObject) {
            ticket = new Ticket();
            ticket.setTicket(jsonObject.getString("ticket"));
            //永久二维码的expire_seconds属性为null
            if (Objects.equals(null,jsonObject.getInteger("expire_seconds"))){
                ticket.setExpire_seconds(0);
            }else {
                ticket.setExpire_seconds(jsonObject.getInteger("expire_seconds"));
            }
            ticket.setUrl("url");
            return ticket;
        } else {
            return ticket = new Ticket("null", 800, "null");
        }
    }


}
