package com.juxing.controller;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Customer;
import com.juxing.service.CodeService;
import com.juxing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 15
 * @Description:
 */

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CodeService codeService;

    @RequestMapping("/customerSave")
    public Resp customerSave(@RequestBody Customer customer) {
        System.out.println("customer" + customer);
        return customerService.customerSave(customer);
    }

    /**
     * 咨询项目
     *
     * @return
     */
    @RequestMapping("/getProjects")
    public RespObj getProjects() {
        String key = "project_name";
        return codeService.selectByKey(key);
    }

    @RequestMapping("/getDesigner")
    public RespObj getDesigner() {
        String key = "designer_name";
        return codeService.selectByKey(key);
    }

    /**
     * 咨询项目和设计师
     */
    @RequestMapping("/getObjes")

    public RespObj getObjes() {

        return codeService.selectAll();

    }


}
