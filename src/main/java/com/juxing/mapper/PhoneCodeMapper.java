package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.PhoneCode;

public interface PhoneCodeMapper {

    int insert(PhoneCode record);

    PhoneCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PhoneCode record);
}