package com.juxing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.util.AdvancedUtil;
import com.juxing.common.util.CommonUtil;
import com.juxing.mapper.UserMapper;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.pojo.wechatPojo.Action_info;
import com.juxing.pojo.wechatPojo.JsonWechatPicReq;
import com.juxing.pojo.wechatPojo.Scene;
import com.juxing.pojo.wechatPojo.Ticket;
import com.juxing.service.WechatQRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/22 17
 * @Description:
 */

@Service
public class WechatQRcodeServiceImpl implements WechatQRcodeService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String createForeverQRcode(String access_Token, String scene_str) {
        String shortUrl = null;
        // scene_str 为1+openId 或2+openId
        User user = userMapper.selectByOpenid(scene_str.substring(1));

        if (Objects.equals(null, user.getUserShorturl())||Objects.equals(0,user.getUserShorturl().length())) {
            //用户信息里没有二维码短链接，生成一个新的
            TreeMap<String, String> params = new TreeMap();
            params.put("access_token", access_Token);
            // output data
            Map<String, String> intMap = new HashMap();
            intMap.put("scene_str", scene_str);
            Map<String, Map<String, String>> mapMap = new HashMap();
            mapMap.put("scene", intMap);
            Map<String, Object> paramsMap = new HashMap();
            paramsMap.put("action_name", "QR_LIMIT_STR_SCENE");
            paramsMap.put("action_info", mapMap);

            String data = JSONObject.toJSONString(paramsMap);
            Ticket ticket = AdvancedUtil.getTicket(access_Token, data);

            String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
            //二维码的图片地址，长链接
            requestUrl = requestUrl.replace("TICKET", ticket.getTicket());
            // 长链接转短链接
            JSONObject jsonObject = CommonUtil.long2short(access_Token, requestUrl);
            // 短链接地址
            shortUrl = jsonObject.getString("short_url");
            user.setUserShorturl(shortUrl);
            userMapper.updateUserShorturl(user);
        } else {
            shortUrl = user.getUserShorturl();
        }
        return shortUrl;
    }

    @Override
    public String createTempQRcode(String accessToken, String sceneStr) {
        //请求参数str（临时）
        JsonWechatPicReq wechatPicReq = new JsonWechatPicReq();
        //临时二维码的有效时间（30天）
        wechatPicReq.setExpire_seconds(2592000L);
        wechatPicReq.setAction_name("QR_STR_SCENE");
        wechatPicReq.setAction_info(new Action_info(new Scene(sceneStr)));

        String post = JSONObject.toJSONString(wechatPicReq);

        Ticket ticket = AdvancedUtil.getTicket(accessToken, post);
        String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        //临时二维码的图片地址，长链接
        requestUrl = requestUrl.replace("TICKET", ticket.getTicket());
        // 长链接转短链接
        JSONObject jsonObject = CommonUtil.long2short(accessToken, requestUrl);
        // 短链接地址
        String shortUrl = jsonObject.getString("short_url");
        return shortUrl;
    }
}
