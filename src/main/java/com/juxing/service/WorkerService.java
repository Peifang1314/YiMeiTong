package com.juxing.service;

import com.juxing.common.vo.MyShops;
import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.pojo.mysqlPojo.User;

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
     * 我的店家
     * @param openId 渠道的openId
     * @param status 店家的状态 (status为2)
     * @param page 第X页
     * @return 渠道下的店家
     */
    RespObj getShopsOfMy(String openId, int status, int page);

    /**
     * 我的店家--根据电话或者店名查找
     * @param openId 渠道的openID
     * @param text 电话或者店名
     * @param page 第X页
     * @return 模糊查询的店家信息（openId、店名、订单总金额）
     */
    RespObj getMyShopsByText(String openId , String text, int page);


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
