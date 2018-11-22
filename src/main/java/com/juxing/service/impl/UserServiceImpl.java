package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.util.SessionUtil;
import com.juxing.common.vo.RespObj;
import com.juxing.common.vo.Resp;
import com.juxing.mapper.PhoneCodeMapper;
import com.juxing.mapper.RelationsMapper;
import com.juxing.mapper.UserInfoMapper;
import com.juxing.mapper.UserMapper;
import com.juxing.miaodiyun.httpApiDemo.IndustrySMS;
import com.juxing.miaodiyun.httpApiDemo.common.RandUtil;
import com.juxing.pojo.mysqlPojo.PhoneCode;
import com.juxing.pojo.mysqlPojo.Relations;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.pojo.reqPojo.SearchRequest;
import com.juxing.pojo.wechatPojo.UserInfo;
import com.juxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/23 14
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RelationsMapper relationsMapper;


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public Resp userSave(User user) {
        String userName = user.getUserName();
        String userPhone = user.getUserPhone();
        Integer userRole = user.getUserRole();
        String userOpenid = user.getUserOpenid();

        User user2 = userMapper.selectByOpenid(userOpenid);
        if (null != user2) {
            return new Resp(200, "用户已注册，审核中", 0);
        } else {
            //判断用户名是否为空
            if (null == userName) {
                return new Resp(800, "用户名不能为空", 0);
            } else {
                if (userPhone.length() != 11) {
                    return new Resp(800, "号码长度不够", 0);
                } else {
                    User user1 = userMapper.selectByPhone(userPhone);
                    //判断号码是否被使用，找到用户说明号码已被注册
                    if (null != user1) {
                        return new Resp(800, "手机号已被注册", 0);
                    } else {
                        //进行注册
                        user.setUserId(IdUtil.getId());
                        user.setUserRole(userRole);
                        if (userMapper.insert(user) > 0) {
                            return new Resp(200, "success", 1);
                        } else {
                            return new Resp(800, "服务器繁忙注册失败", 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 存储或更新用户的微信数据
     *
     * @param userInfo
     * @return
     */
    @Override
    public RespObj saveOrUpdateUserinfo(UserInfo userInfo) throws Exception {
        //  存储用户信息到数据库
        // 1、判断用户是否存在，存在就更新数据并返回ok
        //  2、不存在就存储
        String openid = userInfo.getOpenid();
        //获得session值，存储到数据库内对比操纵
        String session = SessionUtil.getSession();
        System.out.println("session:" + session);
        //数据库内查找的user
        UserInfo userInfo1 = userInfoMapper.selectByOpenid(openid);
        if (null == userInfo1) {
            //用户不存在，存储用户
            System.out.println("用户微信信息不存在，进行存储");
            userInfo.setSession(session);
            if (userInfoMapper.insert(userInfo) > 0) {
                return new RespObj(200, "success", 6, userInfo);
            } else {
                return new RespObj(800, "存储未成功", 0, null);
            }
        } else {
            //用户存在，更新
            System.out.println("用户存在，进行更新");
            userInfo.setSession(session);
            if (userInfoMapper.updateUser(userInfo) > 0) {
//                //将返回数据转为byte数组
//                byte[] bytes = userInfo.toString().getBytes();
//                //公钥加密
//                byte[] obj = RsaUtil.publicEncrypt(bytes, RsaKey.PUBLICKEY);
                return new RespObj(200, "用户更新", 6, userInfo);
            } else {
//                //将返回数据转为byte数组
//                byte[] bytes = user.toString().getBytes();
//                //公钥加密
//                byte[] obj = RsaUtil.publicEncrypt(bytes, RsaKey.PUBLICKEY);
                return new RespObj(200, "更新失败", 0, userInfo);
            }
        }
    }

    /**
     * @return 所有未审核的用户
     */
    @Override
    public RespObj getUnableUsers() {
        List<User> datas = userMapper.selectUnableUser();
        if (null == datas) {
            return new RespObj(800, "error", 0, null);
        } else {
            return new RespObj(200, "success", 1, datas);
        }
    }

    @Override
    public Resp updateUserStatus(String openid) {
        User user = userMapper.selectByOpenid(openid);
        System.out.println("impl-openid:" + openid);
        if (userMapper.updataUserstatus(user) > 0) {
            return Resp.ok();
        } else {
            return Resp.error();
        }
    }

    /**
     * 验证码发送
     *
     * @param phoneCode
     * @return
     */
    @Override
    public RespObj getPhoneCode(PhoneCode phoneCode) {
        String phone = phoneCode.getPhone();
        int code = RandUtil.getRandNum();

        String smsContent = "【巨星集团】您的验证码为" + code + "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
        IndustrySMS.execute(phone, smsContent);

        phoneCode.setCode(code);
        if (phoneCodeMapper.insert(phoneCode) > 0) {
            return new RespObj(200, "success", 1, code);
        } else {
            return new RespObj(800, "error", 0);
        }
    }


    @Override
    public RespObj phoneCheck(String phone) {
        User user = userMapper.selectByPhone(phone);
        if (null == user) {
            return new RespObj(200, "号码已被使用", 1, null);
        } else {
            return new RespObj(800, "号码不可用", 0, null);
        }
    }

    @Override
    public User getUser(String userOpenid) {
        return userMapper.selectByOpenid(userOpenid);
    }

    /**
     * 模糊查询，号码或用户名
     *
     * @param
     * @return
     */
    @Override
    public RespObj getUsers(SearchRequest request) {
        String text = request.getText();
        List<User> lists = userMapper.selectByText(text);

        System.out.println(lists);
        if (null == lists) {
            return new RespObj(800, "error", 0, null);
        } else {
            return new RespObj(200, "success", 1, lists);
        }
    }

}
