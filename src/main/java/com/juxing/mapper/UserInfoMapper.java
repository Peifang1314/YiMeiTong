package com.juxing.mapper;

import com.juxing.pojo.wechatPojo.UserInfo;

public interface UserInfoMapper {

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 更新用户的微信数据
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

    /**
     * 通过openid查找用户的微信数据
     * @param openid
     * @return
     */
    UserInfo selectByOpenid(String openid);


}