package com.juxing.service;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;


/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/8 15
 * @Description:
 */
public interface OrderService {

    /**
     * 订单存储
     *
     * @param orders
     * @return
     */
    Resp orderSave(Orders orders);

    /**
     * @param orderRefer 用户的openid
     * @param status     订单状态 4(已付款) 8(已返款)
     * @param page       第X页
     * @return 该用户的所有订单（不同状态）,分页显示
     */
    RespObj getAllOrders(String orderRefer, int status, int page);

    /**
     * 根据电话或者姓名查找订单
     * @param openId 订单创建者的openId
     * @param text   电话/姓名
     * @param page   第X页
     * @return 模糊查询出的订单
     */
    RespObj getOrdersByText(String openId, String text, int page);

    /**
     * 根据单号或者姓名查找
     * @param openId 订单创建者的openId
     * @param text 单号/姓名
     * @param status 订单状态
     * @param page 第X页
     * @return 模糊查询的订单信息
     */
    RespObj getOrdersByText2(String openId, String text,int status, int page);


    /**
     * @param cusPhone 消费者名称
     * @param page    第X页
     * @return 该消费者的所有订单
     */
    RespObj getOrdersByCusPhone(String cusPhone, int page);

    /**
     * @param openid 用户openid
     * @param page   第X页
     * @return 用户下的所有订单（按消费者分组）
     */
    RespObj getAllCus(String openid, int page);


    /**
     * @param openid 用户openid
     * @param oid    订单号
     * @return 订单号查找用户下的订单
     */
    RespObj getOrdersByOid(String openid, String oid);

    /**
     * 所有需要审核的订单（渠道已审核）
     * @return 提交状态为2的订单
     */
    RespObj getOrdersToFinance();

    /**
     * 财务审核订单
     * @param oid 订单id
     * @return 审核状态 200/800
     */
    Resp updateOrderByFinance(String oid);


}
