package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.Code;

import java.util.List;

public interface CodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Code record);

    Code selectByPrimaryKey(Integer id);

    List<Code> selectByKey(String key);

    int updateByPrimaryKey(Code record);
}