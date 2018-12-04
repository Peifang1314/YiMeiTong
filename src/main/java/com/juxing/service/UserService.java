package com.juxing.service;

import com.juxing.common.vo.RespObj;
import com.juxing.common.vo.Resp;
import com.juxing.pojo.mysqlPojo.PhoneCode;
import com.juxing.pojo.reqPojo.RequestOne;
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
     *
     * @param user
     * @return
     */
    Resp userSave(User user);

    /**
     * 模糊查询获得用户信息
     *
     * @param searchRequest
     * @return
     */
    RespObj getUsers(RequestOne searchRequest);


    /**
     * 用户第一次登录，存储用户的微信数据
     *
     * @param userInfo
     * @return
     */
    RespObj saveUserinfo(UserInfo userInfo);

    /**
     * 更新用户的头像、昵称
     *
     * @param userInfo
     * @return 用户对象
     */
    User updateUser(UserInfo userInfo);

    /**
     * 所有未审核的用户
     *
     * @return
     */
    RespObj getUnableUsers();



    /**
     * 得到用户信息
     *
     * @param userOpenid 用户的微信openID
     * @return
     */
    User getUser(String userOpenid);


    /**
     * 渠道的不同状态店家（选择店家来源，未受理的店家）
     * @param openId 渠道的openId
     * @param status 店家状态
     * @param page 第X页
     * @return 不同状态的用户数据
     */
    RespObj getMyUserByStatus(String openId, int status, int page);

    /**
     * 模糊查询渠道下的店家
     *
     * @param openId   渠道的openId
     * @param shopName 店家的名字
     * @return 渠道下的店家
     */
    RespObj getMyUsersByName(String openId, String shopName);

    /**
     * 新增店家不通过，并填写备注原因
     *
     * @param openId   新增店家的openId
     * @param notAllow 备注原因
     * @return
     */
    Resp setUserNotAllow(String openId, String notAllow);

    /**
     * 店家审核通过
     *
     * @param openid 店家的openId
     * @return
     */
    Resp setUserAllow(String openid);

    //----------------------保留不用-----------------------------//
    //发送手机验证码
    RespObj getPhoneCode(PhoneCode phoneCode);

    /**
     *
     */


    /**
     * 号码是否已经被注册
     * @param phone 要检测的手机号码
     * @return
     */
    Resp phoneCheck(String phone);


}
