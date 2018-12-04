package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
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
import com.juxing.pojo.reqPojo.RequestOne;
import com.juxing.pojo.wechatPojo.UserInfo;
import com.juxing.pojo.wechatPojo.sysUser;
import com.juxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * @param user 注册的用户
     * @return
     */
    @Override
    public Resp userSave(User user) {
        String userName = user.getUserName();
        String userPhone = user.getUserPhone();
        Integer userRole = user.getUserRole();
        String userOpenid = user.getUserOpenid();
        // 管理员信息
        User servicer = userMapper.selectByOpenid(sysUser.openId);
        // 判断用户是否已经存在
        User user2 = userMapper.selectByOpenid(userOpenid);
        // 用户注册先判断用户角色 1 店家 2 渠道

        if (Objects.equals(1, userRole)) {
            // 店家注册
            Relations relation = relationsMapper.selectRelation(userOpenid);
            if (relation != null) {
                // 关系不为空，扫码注册，有上级或渠道
                String serviceOpenid = relation.getServiceId();
                if (serviceOpenid != null) {
                    // 渠道的信息实体类存在
                    User theServicer = userMapper.selectByOpenid(relation.getServiceId());

                    // 用户渠道的信息
                    user.setServiceOpenid(theServicer.getUserOpenid());
                    user.setServiceId(theServicer.getUserId());
                    user.setServiceName(theServicer.getUserName());
                    user.setServicePhone(theServicer.getUserPhone());
                }
            } else {
                // 店家直接注册，管理员信息作为渠道的值
                String serviceId = sysUser.openId;
                Relations theRela = new Relations();
                theRela.setUserId(userOpenid);
                theRela.setServiceId(serviceId);
                relationsMapper.insert(theRela);

                // 管理员信息不为空
                if (servicer != null) {
                    // 管理员信息作为用户渠道的信息
                    user.setServiceOpenid(servicer.getUserOpenid());
                    user.setServiceId(servicer.getUserId());
                    user.setServiceName(servicer.getUserName());
                    user.setServicePhone(servicer.getUserPhone());
                }
            }
        } else {
            // 2 渠道人员注册 管理员信息作为上级和渠道的值
            String serviceId = sysUser.openId;
            Relations theRela = new Relations();
            theRela.setUserId(userOpenid);
            theRela.setServiceId(serviceId);
            // 2.1 查看关系表
            Relations relation = relationsMapper.selectRelation(userOpenid);
            if (Objects.equals(null, relation)) {
                // 2.2 没有关系表存在--》存储
                relationsMapper.insert(theRela);
            } else {
                // 2.3 有关系表存在--》更新为正确的关系信息
                // （解决渠道人员扫描其他带参二维码进入公众号，关系表内数据错误的情况）
                relationsMapper.updateAllRelation(theRela);
            }

            // 管理员信息不为空
            if (servicer != null) {
                // 管理员信息作为用户渠道的信息
                user.setServiceOpenid(servicer.getUserOpenid());
                user.setServiceId(servicer.getUserId());
                user.setServiceName(servicer.getUserName());
                user.setServicePhone(servicer.getUserPhone());
            }

        }
        if (null != user2) {
            // 用户存在
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

    @Override
    public RespObj saveUserinfo(UserInfo userInfo) {
        // 存储用户微信数据要判断是否存在
        String opneId = userInfo.getUserOpenid();
        UserInfo theUser = userInfoMapper.selectByOpenid(opneId);
        if (Objects.equals(null, theUser)) {
            // 用户微信数据不存在--》存储
            if (userInfoMapper.insert(userInfo) > 0) {
                return new RespObj(200, "第一次使用，微信数据存储", 6, userInfo);
            } else {
                return new RespObj(800, "微信数据存储失败", 0, null);
            }
        } else {
            if (userInfoMapper.updateUserInfo(userInfo) > 0) {
                return new RespObj(200, "微信数据存在，更新", 6, userInfo);
            } else {
                return new RespObj(800, "微信数据存储失败", 0, null);
            }
        }
    }

    @Override
    public User updateUser(UserInfo userInfo) {
        User user = null;
        //1、更新数据内的用户微信数据
        userInfoMapper.updateUserInfo(userInfo);
        //2、更新用户的昵称、头像等可变信息
        //判断用户的角色，店家改姓名，渠道不改姓名
        user = userMapper.selectByOpenid(userInfo.getUserOpenid());
        if (Objects.equals(1, user.getUserRole())) {
            //用户角色为1--店家，改昵称、头像、姓名
            user.setUserNickname(userInfo.getNickname());
            user.setUserHeadimgurl(userInfo.getHeadimgurl());
            user.setUserName(userInfo.getNickname());
            userMapper.updateUser(user);
        } else {
            //用户角色为2--渠道，改昵称、头像
            user.setUserNickname(userInfo.getNickname());
            user.setUserHeadimgurl(userInfo.getHeadimgurl());
            userMapper.updateUser(user);
        }
        return userMapper.selectByOpenid(userInfo.getUserOpenid());
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
    public Resp setUserAllow(String openid) {
        User user = userMapper.selectByOpenid(openid);
        if (userMapper.updateUserStatus(user) > 0) {
            return Resp.ok();
        } else {
            return Resp.error();
        }
    }

    @Override
    public Resp phoneCheck(String phone) {
        User user = userMapper.selectByPhone(phone);
        if (null == user) {
            return new Resp(200, "号码已被使用", 1);
        } else {
            return new Resp(800, "号码不可用", 0);
        }
    }

    @Override
    public User getUser(String userOpenid) {
        return userMapper.selectByOpenid(userOpenid);
    }


    @Override
    public RespObj getMyUserByStatus(String openId, int status, int page) {
        List<User> userList = userMapper.selectUsersByStatus(openId, status, page);
        if (Objects.equals(null, userList)) {
            return RespObj.error();
        } else {
            return RespObj.setObjs(userList);
        }
    }

    @Override
    public RespObj getUsers(RequestOne request) {
        String text = request.getText();
        List<User> lists = userMapper.selectByText(text);
        if (null == lists) {
            return new RespObj(800, "error", 0, null);
        } else {
            return new RespObj(200, "success", 1, lists);
        }
    }

    @Override
    public RespObj getMyUsersByName(String openId, String shopName) {
        // 1. 先找到下级店家，然后分页
        List<User> shopUser = null;
        // 1.1 所有下级店家
        List<Relations> relations = relationsMapper.selectUserList(openId);
        // 1.2 下级店家的openId
        List openList = new ArrayList();
        for (Relations rela : relations) {
            openList.add(rela.getUserId());
        }
        // 下级店家存在
        if (openList != null && openList.size() > 0) {
            //模糊查询的店家
            shopUser = userMapper.selectUsersByName(openList, shopName);
            return new RespObj(200, "店家来源", 1, shopUser);
        } else {
            return RespObj.error();
        }
    }

    @Override
    public Resp setUserNotAllow(String openId, String notAllow) {
        int i = userMapper.updateUserNotAllow(openId, notAllow);
        if (i > 0) {
            return new Resp(200, "拒绝原因写入", 1);
        } else {
            return Resp.error();
        }
    }

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

}
