package com.juxing.service.impl;

import com.juxing.common.vo.MyShops;
import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.mapper.NewsMapper;
import com.juxing.mapper.OrdersMapper;
import com.juxing.mapper.RelationsMapper;
import com.juxing.mapper.UserMapper;
import com.juxing.pojo.mysqlPojo.News;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.pojo.mysqlPojo.Relations;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/26 14
 * @Description:
 */
@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelationsMapper relationsMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Map workerIndex(String openId) {
        Map retMap = new HashMap();
        // 1. 未受理新增店家
        List<User> shopUser = null;
        // 1.1 所有下级店家
        List<Relations> relations = relationsMapper.selectUserList(openId);
        // 1.2 下级店家的openId
        List openList = new ArrayList();
        for (Relations rela : relations) {
            openList.add(rela.getUserId());
        }

        if (openList != null && openList.size() > 0) {
            shopUser = userMapper.selectUnableUserByServ(openList);
        }
        if (Objects.equals(null, shopUser)) {
            retMap.put("shopUser", 0);
        } else {
            retMap.put("shopUser", shopUser.size());
        }


        // 2. 未受理预约客户
        List<Orders> ordersList = ordersMapper.selectByServiceOpenid(openId);
        if (Objects.equals(null, ordersList)) {
            retMap.put("cusNum", 0);
        } else {
            retMap.put("cusNum", ordersList.size());
        }

        // 3. 其他未受理
        retMap.put("otherNum", 0);

        // 4. 首页-我的返款
        // 4.1 我提交的订单返款
        List<Orders> orders = ordersMapper.selectAllOrders(openId);
        int i = 0;
        for (Orders oders : orders) {
            i = i + Integer.valueOf(oders.getOrderDismoney());
        }
        retMap.put("disMoney", i);


        // 5. 最新的3条新闻
        List<News> newsList = newsMapper.selectThree();
        if (Objects.equals(null, newsList)) {
            //新闻数据
            retMap.put("newsList", 0);
        } else {
            retMap.put("newsList", newsList);
        }
        //所有新闻
        List<News> news = newsMapper.selectAllNews();
        if (Objects.equals(null, news)) {
            retMap.put("newsNum", 0);
        } else {
            //新闻总数
            retMap.put("newsNum", news.size());
        }
        // 6. 奖励

        // 7. 我的店家数量
        retMap.put("shopNum", relations.size());
        return retMap;
    }

    @Override
    public RespObj myReOrders(String openId) {
        List<Orders> ordersList = ordersMapper.selectByServiceOpenid(openId);
        if (Objects.equals(null, ordersList)) {
            return RespObj.error();
        } else {
            return RespObj.setObjs(ordersList);
        }
    }

    @Override
    public RespObj getShopsOfMy(String openId, int status, int page) {
        int i = 0;
        MyShops myShop = null;
        List<MyShops> myShopsList = new ArrayList();
        List<User> userList = userMapper.selectUsersByStatus(openId, status, page);
        System.out.println("userList:" + userList.size());
        if (userList.isEmpty() || Objects.equals(null, userList)) {
            // 渠道下没有店铺
            return RespObj.error();
        } else {
            for (int j = 0; j < userList.size(); j++) {
                // 店铺的openId 和 店名
                myShop = new MyShops();
                myShop.setOpenId(userList.get(j).getUserOpenid());
                myShop.setShopName(userList.get(j).getUserShopname());

                // 下级店铺的所有订单返款金额
                List<Orders> ordersList = ordersMapper.selectAllOrders2(userList.get(j).getUserOpenid(), 8);
                if (Objects.equals(null, ordersList) || ordersList.isEmpty()) {
                    myShop.setNum(i);
                } else {
                    for (Orders order : ordersList) {
                        i = i + Integer.valueOf(order.getOrderDismoney());
                        myShop.setNum(i);
                    }
                }
                myShopsList.add(myShop);
            }
            if (Objects.equals(null, myShopsList)) {
                return RespObj.error();
            } else {
                return RespObj.setObjs(myShopsList);
            }
        }
    }

    @Override
    public RespObj getMyShopsByText(String openId, String text, int page) {
        int i = 0;
        List<User> userList = userMapper.selectByText2(openId, text, page);
        MyShops myShop = null;
        List<MyShops> myShopsList = new ArrayList();
        if (userList.isEmpty() || Objects.equals(null, userList)) {
            // 模糊查询没有数据
            return RespObj.error();
        } else {
            for (int j = 0; j < userList.size(); j++) {
                // 店铺的openId 和 店名
                myShop = new MyShops();
                myShop.setOpenId(userList.get(j).getUserOpenid());
                myShop.setShopName(userList.get(j).getUserShopname());

                // 下级店铺的所有订单返款金额
                List<Orders> ordersList = ordersMapper.selectAllOrders2(userList.get(j).getUserOpenid(), 8);
                if (Objects.equals(null, ordersList) || ordersList.isEmpty()) {
                    myShop.setNum(i);
                } else {
                    for (Orders order : ordersList) {
                        i = i + Integer.valueOf(order.getOrderDismoney());
                        myShop.setNum(i);
                    }
                }
                myShopsList.add(myShop);
            }
            if (Objects.equals(null, myShopsList)) {
                return RespObj.error();
            } else {
                return RespObj.setObjs(myShopsList);
            }
        }
    }


    @Override
    public RespObj getOneOrder(String oid) {
        Orders order = ordersMapper.selectOne(oid);
        if (Objects.equals(null, order)) {
            return RespObj.error();
        } else {
            return RespObj.setObjs(order);
        }
    }

    @Override
    public Resp getOrderPass(Orders order) {
        Orders theOrder = ordersMapper.selectOne(order.getOrderId());
        int orderSubStatus = theOrder.getOrderSubstatus();
        if (Objects.equals(2, orderSubStatus)) {
            //渠道已审核订单，提交
            return new Resp(800, "订单已提交至财务,请勿重复提交", 0);
        } else {
            int i = ordersMapper.updateByOid(order);
            if (i > 0) {
                return Resp.ok();
            } else {
                return new Resp(800, "审核失败，联系管理员", 0);
            }
        }
    }
}
