package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.User;

import java.util.List;

public interface UserMapper {

    //用户注册（角色信息前端设置）
    int insert(User user);

    //号码检查
    User selectByPhone(String phone);

    //根据openid检测是否注册过
    User selectByOpenid(String openid);

    //模糊查询
    List<User> selectByText(String text);

    //所有未审核的用户
    List<User> selectUnableUser();

    //更改用户状态
    int updataUserstatus(User user);

    //更改用户的信息
    int updateUser(User user);


    User selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(User record);
}