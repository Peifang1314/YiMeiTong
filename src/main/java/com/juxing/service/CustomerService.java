package com.juxing.service;

import com.juxing.common.vo.Resp;
import com.juxing.pojo.mysqlPojo.Customer;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 15
 * @Description:
 */
public interface CustomerService {

    /**
     * 顾客新增，该功能合并到订单新增内
     * @param customer
     * @return
     */
    Resp customerSave(Customer customer);

}
