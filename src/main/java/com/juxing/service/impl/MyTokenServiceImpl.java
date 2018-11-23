package com.juxing.service.impl;

import com.juxing.common.util.CommonUtil;
import com.juxing.common.util.DateUtil;
import com.juxing.common.vo.RespObj;
import com.juxing.mapper.MyTokenMapper;
import com.juxing.pojo.wechatPojo.App;
import com.juxing.pojo.wechatPojo.MyToken;
import com.juxing.pojo.wechatPojo.Token;
import com.juxing.service.MyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/9 17
 * @Description:
 */
@Service
public class MyTokenServiceImpl implements MyTokenService {

    @Autowired
    private MyTokenMapper myTokenMapper;

    /*
    先检查数据库内token值
    1、没有数据，创建一个新的token
    2、判断token是否过期，过期就创建一个新的token
    3、未过期就返回token
     */
    @Override
    public RespObj getMyToken() {

        MyToken myToken = null;
        myToken = myTokenMapper.selectOne();
        if (null == myToken) {
            myToken = new MyToken();
            //数据库内没有AccessToken令牌，第一次时出现
            Token token = CommonUtil.getToken(App.APPID, App.APPSECRET);
            System.out.println("没有token值，生成新的token：" + token);
            myToken.setAccessToken(token.getAccessToken());
            myToken.setExpiresin(token.getExpiresIn());
            myTokenMapper.insert(myToken);
            return new RespObj(200, "success", 1, myToken);
        } else {
            //得到token的创建时间
            Date createtime = myToken.getCreatetime();
            //将数据库内带.0的时间格式化为字符串
            String s = DateUtil.formatDate(createtime);
            //将字符串时间解析，并得到时间戳
            long time = DateUtil.stringToDate(s).getTime();
            //判断token是否过期
            int t = (int) ((System.currentTimeMillis() - time) / 1000);

            if (t < 5400) {
                //token未过期，设置有效时长为1.5个小时，官方时间为2小时
                return new RespObj(200, "token未过期", 1, myToken);
            } else {
                //token过期，生成新token
                Token newToken = CommonUtil.getToken(App.APPID, App.APPSECRET);
                MyToken newMyToken = new MyToken();
                newMyToken.setAccessToken(newToken.getAccessToken());
                newMyToken.setExpiresin(newToken.getExpiresIn());
                if (null != newToken) {
                    //存储到数据库
                    if (myTokenMapper.updateOne(newMyToken) > 0) {
                        //更新数据库内token成功
                        return new RespObj(200, "数据库内token更新成功", 1, newMyToken);
                    } else {
                        return new RespObj(200, "数据库内token更新未成功", 0, newMyToken);
                    }
                } else {
                    return new RespObj(800, "token未生成，联系管理员", 0, null);
                }
            }
        }
    }


    @Override
    public String getAccessToken() {
        MyToken myToken = myTokenMapper.selectOne();
        return myToken.getAccessToken();
    }
}
