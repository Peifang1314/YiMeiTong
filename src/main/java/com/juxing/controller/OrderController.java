package com.juxing.controller;

import com.juxing.common.vo.Resp;

import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.pojo.reqPojo.SearchRequest;
import com.juxing.pojo.reqPojo.SearchRequestList;
import com.juxing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/8 15
 * @Description: 订单controller层
 */

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单生成，并存储用户
     *
     * @param orders 订单实体类
     * @return
     */
    @RequestMapping("/orderSave")
    public Resp orderSave(@RequestBody Orders orders) {
        return orderService.orderSave(orders);
    }

    /**
     * 获得用户不同状态下的订单
     *
     * @param request 订单查询实体类
     * @return
     */
    @RequestMapping("/getAllOrders")
    public RespObj getAllOrders(@RequestBody SearchRequestList request) {
        return orderService.getAllOrders(request.getText(), request.getNum(), request.getPage());
    }

    /**
     * 用户所有订单（没有状态区分）
     *
     * @param request 订单查询实体类
     * @return
     */
    @RequestMapping("/getAllCus")
    public RespObj getAllCus(@RequestBody SearchRequestList request) {
        return orderService.getAllCus(request.getText(), request.getPage());
    }

    /**
     * 消费者的所有订单
     *
     * @param request 订单查询实体类
     * @return 获得消费者的所有订单(以电话号码区分)
     */
    @RequestMapping("/getOrdersByCusPhone")
    public RespObj getOrdersByCusName(@RequestBody SearchRequestList request) {
        return orderService.getOrdersByCusPhone(request.getText(), request.getPage());
    }

    /**
     * @param request 查询实体类
     * @return 模糊查询（姓名/电话）该用户的订单信息
     */
    @RequestMapping("/getCusByText")
    public RespObj getOrdersByText(@RequestBody SearchRequestList request) {
        return orderService.getOrdersByText(request.getText(), request.getText1(), request.getPage());
    }


    /**
     * @param request
     * @return 根据订单号查询出的订单
     */
    @RequestMapping("/getOrdersByOid")
    public RespObj getOrdersByOid(@RequestBody SearchRequestList request) {
        return orderService.getOrdersByOid(request.getText(), request.getText1());
    }


}
