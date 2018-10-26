package com.juxing.controller;

import com.juxing.common.vo.R;
import com.juxing.pojo.Customer;
import com.juxing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 15
 * @Description:
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customerSave")
    public R customerSave(Customer customer){
        return customerService.customerSave(customer);
    }


}
