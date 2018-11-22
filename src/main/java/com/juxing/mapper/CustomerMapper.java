package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    int insert(Customer record);

    /**
     * 根据电话号码查找消费者
     * @param phone 消费者的电话号码
     * @return
     */
    Customer selectByPhone(String phone);

    /**
     * @param openid 消费者所属的用户openid
     * @return 该用户下的消费者
     */
    List<Customer> selectByOpenid(String openid);


    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Customer record);


}