package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.vo.Resp;
import com.juxing.mapper.CustomerMapper;
import com.juxing.pojo.mysqlPojo.Customer;
import com.juxing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Objects;

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
    public Resp customerSave(Customer customer) {

        if (Objects.equals(null, customer.getCusName())) {
            return new Resp(800, "姓名不能为空", 0);
        } else {
            if (customerMapper.selectByPhone(customer.getCusPhone()) != null) {
                return new Resp(800, "号码已被使用", 0);
            } else {
                customer.setCusId(IdUtil.getId());

//                //时间戳转字符串
//                customer.setCometime(IdUtil.time2String(Long.valueOf(customer.getCometime())));

                if (customerMapper.insert(customer) > 0) {
                    return Resp.ok();
                } else {
                    return Resp.error();
                }
            }
        }
    }
}
