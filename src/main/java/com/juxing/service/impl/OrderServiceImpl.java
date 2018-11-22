package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.mapper.CustomerMapper;
import com.juxing.mapper.OrdersMapper;
import com.juxing.pojo.mysqlPojo.Customer;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/8 15
 * @Description:
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CustomerMapper customerMapper;

    //订单生成
    @Override
    public Resp orderSave(Orders orders) {
        //先存储顾客，再生成订单
        Customer cus = customerMapper.selectByPhone(orders.getCusPhone());
        if (null == cus) {
            String strId = IdUtil.getId();
            //用户不存在，进行存储
            Customer customer = new Customer();
            customer.setCusId(strId);
            customer.setCusName(orders.getCusName());
            customer.setCusRefer(orders.getOrderRefer());
            customer.setCusSex(orders.getCusSex());
            customer.setCusPhone(orders.getCusPhone());
            if (customerMapper.insert(customer) > 0) {
                orders.setOrderId(IdUtil.getId());
                //存储用户的ID
                orders.setCusId(strId);
                int i = ordersMapper.insert(orders);
                if (i > 0) {
                    return new Resp(200, "用户存储，订单生成", 1);
                } else {
                    return new Resp(200, "用户存储，订单未生成", 0);
                }
            } else {
                return new Resp(200, "用户未存储,订单未生成", 0);
            }
        } else {
            orders.setOrderId(IdUtil.getId());
            //用户存在，存储用户的ID
            orders.setCusId(cus.getCusId());
            //生成订单
            if (ordersMapper.insert(orders) > 0) {
                return new Resp(200, "用户存在，订单生成", 1);
            } else {
                return new Resp(200, "用户存在，订单未生成", 0);
            }
        }
    }

    //用户所有订单
    @Override
    public RespObj getOrdersByCusPhone(String cusPhone, int page) {
        List<Orders> ordersList = ordersMapper.selectByCus(cusPhone, page);
        System.out.println("消费者订单："+ordersList);
        if (null!=ordersList){
            return new RespObj(200,"success",1,ordersList);
        }else {
            return RespObj.error();
        }
    }

    // 根据状态码查询对应的所有订单
    @Override
    public RespObj getAllOrders(String orderRefer, int status, int page) {
        List<Orders> ordersList = ordersMapper.selectByRefer(orderRefer, status, page);
        if (null != ordersList) {
            return new RespObj(200, "success", 1, ordersList);
        } else {
            return RespObj.error();
        }
    }


    //模糊查询订单（电话/姓名）
    @Override
    public RespObj getOrdersByText(String openid, String text, int page) {
        List<Orders> ordersList = ordersMapper.selectByText(openid, text, page);
        if (null != ordersList) {
            return new RespObj(200, "success", 1, ordersList);
        } else {
            return RespObj.error();
        }
    }



    //该用下所有消费者
    @Override
    public RespObj getAllCus(String openid, int page) {
        List<Orders> ordersList = ordersMapper.selectByOpenid(openid, page);
        if (null != ordersList) {
            return new RespObj(200, "success", 1, ordersList);
        } else {
            return RespObj.error();
        }
    }

    //根据订单号查找订单
    @Override
    public RespObj getOrdersByOid(String openid, String  oid) {
        Orders order = ordersMapper.selectByOid(openid, oid);
        if (null != order) {
            return new RespObj(200, "success", 1, order);
        } else {
            return RespObj.error();
        }
    }




}
