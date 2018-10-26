package com.juxing.mapper;

import com.juxing.pojo.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Customer record);
}