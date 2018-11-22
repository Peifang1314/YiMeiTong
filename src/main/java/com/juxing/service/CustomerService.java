package com.juxing.service;

import com.juxing.common.vo.Resp;
import com.juxing.pojo.mysqlPojo.Customer;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 15
 * @Description:
 */
public interface CustomerService {

    Resp customerSave(Customer customer);

}
