package com.juxing.service.impl;

import com.juxing.common.vo.RespObj;
import com.juxing.mapper.PhoneCodeMapper;
import com.juxing.mapper.UserMapper;
import com.juxing.pojo.mysqlPojo.PhoneCode;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.service.GetCodeService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/14 14
 * @Description: 获得手机的验证码
 */

@Service
public class GetCodeServiceImpl implements GetCodeService {

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;
    @Autowired
    private UserMapper userMapper;

    private static String Url = "http://106.wx96.com/webservice/sms.php?method=Submit";

    /**
     * @param phone 手机号码
     * @return
     */
    public RespObj getAuthCode(String phone) {

        User user = userMapper.selectByPhone(phone);
        if (Objects.equals(null,user)){
            System.out.println("=============调用获取验证码接口开始============");
            //发起http请求
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(Url);
            client.getParams().setContentCharset("GBK");
            method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

            //短信内容+验证码
            int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
            String content = "您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。";


            NameValuePair[] data = {//提交短信
                    new NameValuePair("account", "C63238281"), //用户名请登录用户中心->验证码、通知短信->帐户及签名设置->APIID
                    new NameValuePair("password", "e534949a32fc8d9a3ef26c8aff890ef5"),  //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                    new NameValuePair("mobile", phone),
                    new NameValuePair("content", content),
            };
            //数据放到请求体
            method.setRequestBody(data);

            try {
                //执行发送
                client.executeMethod(method);
                //得到响应数据
                String SubmitResult = method.getResponseBodyAsString();
                //解析响应数据
                Document doc = DocumentHelper.parseText(SubmitResult);
                Element root = doc.getRootElement();

                String code = root.elementText("code");
                String msg = root.elementText("msg");
                String smsid = root.elementText("smsid");

                System.out.println("短信发送平台返回数据：--"+"code:--"+code+"msg:--"+msg+"smsid:--"+smsid);

                PhoneCode phoneCode = new PhoneCode();
                phoneCode.setPhone(phone);
                phoneCode.setCode(mobile_code);
                phoneCodeMapper.insert(phoneCode);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return new RespObj(200,"success",1,mobile_code);
        }else {
            return new RespObj(800,"号码已被注册",0,null);
        }



    }


}
