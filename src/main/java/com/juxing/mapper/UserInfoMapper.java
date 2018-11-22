package com.juxing.mapper;

import com.juxing.pojo.wechatPojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserInfo record);

    int updateUser(UserInfo userInfo);

    UserInfo selectByOpenid(String openid);


}