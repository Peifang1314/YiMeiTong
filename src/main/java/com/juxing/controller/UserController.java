package com.juxing.controller;

import com.juxing.common.vo.R;
import com.juxing.common.vo.registResp;
import com.juxing.pojo.PhoneCode;
import com.juxing.pojo.User;
import com.juxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/24 10
 * @Description:
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/userRegist")
    public registResp userSave(@RequestBody User user) {
        return userService.userSave(user);
    }

    /**
     * 手机验证码发送
     * @param phoneCode
     * @return
     */
    @RequestMapping("/getPhoneCode")
    public R phoneCode(@RequestBody PhoneCode phoneCode){

        return userService.getPhoneCode(phoneCode);

    }



    /**
     * 模糊查询，电话号码或名字
     * @param text 查询的内容
     * @return
     */
    @RequestMapping("/getUsers")
    public R getUser(@RequestBody String text) {
        return userService.getUser(text);
    }


    ////////////////暂时不用//////////////////////////////
    /**
     * 店家注册
     *
     * @param user
     * @return
     */
    @PostMapping("/shopRegist")
    public R shopSave(User user) {
        return userService.shopSave(user);
    }

    /**
     * 手机号码检测
     *
     * @param phone 要检测的号码
     * @return
     */
    @RequestMapping("/phoneCheck")
    public R phoneCheck(String phone) {
        return userService.phoneCheck(phone);
    }


}
