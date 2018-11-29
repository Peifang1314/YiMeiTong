package com.juxing.controller.common;

import com.juxing.common.vo.Resp;

import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.pojo.reqPojo.RequestList;
import com.juxing.service.DesignerService;
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
    @Autowired
    private DesignerService designerService;

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
    public RespObj getAllOrders(@RequestBody RequestList request) {
        return orderService.getAllOrders(request.getText(), request.getNum(), request.getPage());
    }

    /**
     * 用户所有订单（没有状态区分）
     *
     * @param request 订单查询实体类
     * @return
     */
    @RequestMapping("/getAllCus")
    public RespObj getAllCus(@RequestBody RequestList request) {
        String openId = request.getText();
        int page = request.getPage();
        return orderService.getAllCus(openId, page);
    }

    /**
     * 消费者的所有订单
     *
     * @param request 订单查询实体类
     * @return 获得消费者的所有订单(以电话号码区分)
     */
    @RequestMapping("/getOrdersByCusPhone")
    public RespObj getOrdersByCusName(@RequestBody RequestList request) {
        return orderService.getOrdersByCusPhone(request.getText(), request.getPage());
    }

    /**
     * 根据姓名或者电话查找
     *
     * @param request 查询实体类
     * @return 模糊查询（姓名/电话）该用户的订单信息
     */
    @RequestMapping("/getCusByText")
    public RespObj getOrdersByText(@RequestBody RequestList request) {
        String openId = request.getText();
        String text = request.getText1();
        int page = request.getPage();
        return orderService.getOrdersByText(openId, text, page);
    }

    /**
     * 根据单号或者姓名查找
     *
     * @param request 订单创建者的openId，单号或者姓名，订单状态，第X页
     * @return 模糊查询的订单信息
     */
    @RequestMapping("/getCusByText2")
    public RespObj getOrdersByText2(@RequestBody RequestList request) {
        String openId = request.getText();
        String text = request.getText1();
        int num = request.getNum();
        int page = request.getPage();
        return orderService.getOrdersByText2(openId, text, num, page);
    }


    /**
     * @param request
     * @return 根据订单号查询出的订单
     */
    @RequestMapping("/getOrdersByOid")
    public RespObj getOrdersByOid(@RequestBody RequestList request) {
        return orderService.getOrdersByOid(request.getText(), request.getText1());
    }

    /**
     * @return 所有的设计师
     */
    @RequestMapping("/getDesigners")
    public RespObj getDesigners() {
        return designerService.getDesigners();
    }


}
