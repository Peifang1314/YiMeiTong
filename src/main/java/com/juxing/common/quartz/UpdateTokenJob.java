package com.juxing.common.quartz;

import com.juxing.common.util.CommonUtil;
import com.juxing.common.util.DateUtil;
import com.juxing.mapper.MyTokenMapper;
import com.juxing.pojo.wechatPojo.App;
import com.juxing.pojo.wechatPojo.MyToken;
import com.juxing.pojo.wechatPojo.Token;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/23 15
 * @Description:
 * 定时任务，更新access_Token（每半小时）
 */
public class UpdateTokenJob {

    @Autowired
    private MyTokenMapper myTokenMapper;

    public void updateTokenJob(){
        System.out.println("定时开始执行"+ DateUtil.getNow());
        Token token = CommonUtil.getToken(App.APPID,App.APPSECRET);
        MyToken newToken = new MyToken();
        newToken.setAccessToken(token.getAccessToken());
        myTokenMapper.updateOne(newToken);
    }
}
