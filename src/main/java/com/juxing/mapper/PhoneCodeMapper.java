package com.juxing.mapper;

import com.juxing.pojo.PhoneCode;
import org.apache.ibatis.annotations.Param;

public interface PhoneCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneCode record);

    PhoneCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PhoneCode record);
}