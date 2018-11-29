package com.juxing.service;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/26 14
 * @Description: 渠道首页的service层
 */
public interface WorkerService {

    /**
     * 渠道商的首页数据
     *
     * @param openId 渠道的openId
     * @return 首页的数据
     */
    Map workerIndex(String openId);

    /**
     * 未受理的预约顾客
     * @param openId 渠道的openId
     * @return 未受理的订单信息(orderSbuStatus为1)
     */
    RespObj myReOrders(String openId);

    /**
     * 修改订单
     * @param oid 订单的ID
     * @return 单个订单信息
     */
    RespObj getOneOrder(String oid);

    /**
     * 订单审核
     * @param order 订单的实体类
     * @return 修改成功，0k;失败，error
     */
    Resp getOrderPass(Orders order);

}
