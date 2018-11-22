package com.juxing.mapper;

import com.juxing.pojo.wechatPojo.MyToken;

public interface MyTokenMapper {
    //查找数据库，判断token是否存在，只使用一次
    MyToken selectOne();

    int updateOne(MyToken myToken);

    int insert(MyToken record);

    MyToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(MyToken record);
}