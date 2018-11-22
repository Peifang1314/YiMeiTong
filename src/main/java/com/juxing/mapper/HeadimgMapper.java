package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.Headimg;

import java.util.List;

public interface HeadimgMapper {

    int insert(Headimg record);

    Headimg selectByPrimaryKey(Integer id);

    List<Headimg> selectAllByTime();

    int updateByPrimaryKey(Headimg record);
}