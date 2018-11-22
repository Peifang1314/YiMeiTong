package com.juxing.controller;

import com.juxing.common.util.RsaUtil;
import com.juxing.common.vo.RespObj;
import com.juxing.common.vo.Resp;
import com.juxing.pojo.*;
import com.juxing.pojo.mysqlPojo.PhoneCode;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.pojo.reqPojo.SearchRequest;
import com.juxing.pojo.reqPojo.UpdateRequest;
import com.juxing.service.GetCodeService;
import com.juxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/24 10
 * @Description:
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GetCodeService getCodeService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/userRegist")
    public Resp userSave(@RequestBody User user) {
        return userService.userSave(user);
    }

    /**
     * 手机验证码发送
     * @param request 请求实体类
     * @return
     */
    @RequestMapping("/getPhoneCode")
    public RespObj phoneCode(@RequestBody SearchRequest request) {
        return getCodeService.getAuthCode(request.getText());
    }

    /**
     * 模糊查询，电话号码或名字
     *
     * @param searchRequest 查询的对象
     * @return
     */
    @RequestMapping("/getUsers")
    public RespObj getUsers(@RequestBody SearchRequest searchRequest) {

        return userService.getUsers(searchRequest);
    }

    /**
     * 查询所有未授权用户
     *
     * @return
     */
    @RequestMapping("/getUnableUsers")
    public RespObj getUnableUsers() {
        return userService.getUnableUsers();
    }


    /**
     * 更改用户状态，审核--已通过
     *
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public Resp updateUserStaus(@RequestBody UpdateRequest updateRequest) {
        return userService.updateUserStatus(updateRequest.getOpenid());
    }


    /**
     * RSA解密
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/rsa")
    public RespObj rsa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String obj = request.getParameter("obj");
        System.out.println("obj--:" + obj);

        byte[] msg = null;
        try {
            //将数据解析成字节码
            byte[] bytes = RsaUtil.base642Byte(obj);
            //私钥解密
            msg = RsaUtil.privateDecrypt(bytes, RsaUtil.string2PrivateKey(RsaKey.PRIVATEKEY));
            System.out.println("私钥解密：" + new String(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = new String(msg);
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = RsaUtil.string2PublicKey(RsaKey.PUBLICKEY);
        //用公钥加密
        byte[] publicEncrypt = RsaUtil.publicEncrypt(message.getBytes(), publicKey);
        //加密后的内容Base64编码
        String byte2Base64 = RsaUtil.byte2Base64(publicEncrypt);
        System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);

        return new RespObj(200, "success", 1, byte2Base64);
    }


    //-----------------暂时不用-----------------//


    /**
     * 手机号码检测
     *
     * @param phone 要检测的号码
     * @return
     */
    @RequestMapping("/phoneCheck")
    public RespObj phoneCheck(String phone) {
        return userService.phoneCheck(phone);
    }


    /**
     * 测试接口
     *
     * @return
     */
    @RequestMapping("/test")
    public Resp test() {
        return new Resp(200, "success", 1);
    }
}
