package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.vo.R;
import com.juxing.mapper.CustomerMapper;
import com.juxing.pojo.Customer;
import com.juxing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 15
 * @Description:
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public R customerSave(Customer customer) {
        customer.setCusId(IdUtil.getId());
        if (customerMapper.insert(customer)>0){
            return R.ok();
        }
        return R.error();
    }
}
