package com.juxing.mapper;

import com.juxing.pojo.User;

import java.util.List;

public interface UserMapper {


    //店家注册
    int shopInsert(User record);
    //渠道注册
    int userInsert(User user);

    //用户注册（角色信息前端设置）
    int insert(User user);



    //号码检查
    User selectByPhone(String phone);

    //模糊查询
    List<User> selectByText(String text);

    User selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(User record);
}