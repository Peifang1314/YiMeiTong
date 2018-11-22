package com.juxing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.util.AdvancedUtil;
import com.juxing.common.util.CommonUtil;
import com.juxing.mapper.UserMapper;
import com.juxing.pojo.wechatPojo.Ticket;
import com.juxing.service.WechatQRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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

        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", access_Token);
        // output data
        Map<String, String> intMap = new HashMap<>();
        intMap.put("scene_str", scene_str);
        Map<String, Map<String, String>> mapMap = new HashMap<>();
        mapMap.put("scene", intMap);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("action_name", "QR_LIMIT_STR_SCENE");
        paramsMap.put("action_info", mapMap);

        String data = JSONObject.toJSONString(paramsMap);
        Ticket ticket= AdvancedUtil.getTicket(access_Token,data);

        String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        //二维码的图片地址，长链接
        requestUrl = requestUrl.replace("TICKET", ticket.getTicket());
        // 长链接转短链接
        JSONObject jsonObject = CommonUtil.long2short(access_Token, requestUrl);
        // 短链接地址
        String shortUrl = jsonObject.getString("short_url");

        return shortUrl;
    }
}
