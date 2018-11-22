package com.juxing.service;

import com.juxing.common.vo.RespObj;
import com.juxing.common.vo.Resp;
import com.juxing.pojo.mysqlPojo.PhoneCode;
import com.juxing.pojo.reqPojo.SearchRequest;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.pojo.wechatPojo.UserInfo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/23 14
 * @Description: 用户CRUD的service层
 */
public interface UserService {

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    Resp userSave(User user);

    /**
     * 模糊查询获得用户信息
     * @param searchRequest
     * @return
     */
    RespObj getUsers(SearchRequest searchRequest);


    /**
     * 用户第一次登录，存储用户的微信数据
     * @param userInfo
     * @return
     */
    RespObj saveUserinfo(UserInfo userInfo);

    /**
     * 更新用户的头像、昵称
     * @param userInfo
     * @return 用户对象
     */
    User updateUser(UserInfo userInfo);

    /**
     * 所有未审核的用户
     * @return
     */
    RespObj getUnableUsers();

    /**
     * 更改用户状态为已审核
     * @param openid
     * @return
     */
    Resp updateUserStatus(String openid);

    /**
     * 得到用户信息
     * @param userOpenid 用户的微信openID
     * @return
     */
    User getUser(String userOpenid);



//----------------------保留不用-----------------------------//
    //发送手机验证码
    RespObj getPhoneCode(PhoneCode phoneCode);

    //号码是否使用检查
    RespObj phoneCheck(String phone);



}
