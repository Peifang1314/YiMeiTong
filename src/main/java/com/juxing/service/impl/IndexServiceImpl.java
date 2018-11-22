package com.juxing.service.impl;

import com.juxing.common.vo.RespObj;
import com.juxing.mapper.*;
import com.juxing.pojo.mysqlPojo.*;
import com.juxing.service.IndexService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/13 10
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private RelationsMapper relationsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomerMapper customerMapper;

    //首页map
    @Override
    public RespObj getIndex(String openid) {

        Map map = new HashMap();
        //1、最新的3条新闻
        List<News> newsList = newsMapper.selectThree();
        if (Objects.equals(null,newsList)) {
            //新闻数据
            map.put("newsList", 0);
        } else {
            map.put("newsList", newsList);
        }
        //所有新闻
        List<News> news = newsMapper.selectAllNews();
        if (Objects.equals(null,news)) {
            map.put("newsNum", 0);
        } else {
            //新闻总数
            map.put("newsNum", news.size());
        }

        //2、我的返款
        //2.1、找到所有关联订单，查询返款金额
        int i = 0;
        List<Orders> ordersList = ordersMapper.selectAllOrders(openid);
        if (Objects.equals(null, ordersList)) {
            i = 0;
        } else {
            for (Orders order : ordersList) {
                i = i + Integer.valueOf(order.getOrderDismoney());
            }
        }
        //返款金额
        map.put("disMoney", i);
        //3、我的下级店家
        List<Relations> relationSon = relationsMapper.selectSonList(openid);
        if (Objects.equals(null,relationSon)) {
            map.put("sonShop", 0);
        } else {
            //下级店家
            map.put("sonShop", relationSon.size());
        }
        //4、我的信息
        User user = userMapper.selectByOpenid(openid);
        if (Objects.equals(null,user)) {
            //用户信息
            map.put("user", 0);
        } else {
            map.put("user", user);
        }
        return new RespObj(200, "success", 1, map);
    }


    //我的模块map
    @Override
    public RespObj getMyInfo(String openid) {

        Map map = new HashMap();
        //1、用户所有订单
        List<Orders> ordersList = ordersMapper.selectAllOrders(openid);
        if (Objects.equals(null, ordersList)) {
            map.put("orderNum", 0);
        } else {
            //订单总数
            map.put("orderNum", ordersList.size());
        }

        //2、客户人数
        List<Customer> customerList = customerMapper.selectByOpenid(openid);
        if (Objects.equals(null,customerList)) {
            //客户人数
            map.put("cusNum", 0);
        } else {
            map.put("cusNum", customerList.size());
        }
        //3、我的下级店家
        List<Relations> relationSon = relationsMapper.selectSonList(openid);
        if (Objects.equals(null,relationSon)) {
            map.put("sonShop", 0);
        } else {
            //下级店家
            map.put("sonShop", relationSon.size());
        }

        //4、总收益(订单返款  +  作为上级返款)
        //4.1 我的返款
        int i = 0;
        if (Objects.equals(null, ordersList)) {
            i = 0;
        } else {
            for (Orders order : ordersList) {
                i = i + Integer.valueOf(order.getOrderDismoney());
            }
        }
        //4.2 作为上级的返款金额
        int j = 0;
        List<Orders> ordersList1 = ordersMapper.selectAllOrdersByFatopenid(openid);
        if (Objects.equals(null, ordersList1)) {
            j = 0;
        } else {
            for (Orders order : ordersList1) {
                j = j + Integer.valueOf(order.getOrderFatherdismoney());
            }
        }
        //总收益
        int sumMoney = i + j;
        //总收益
        map.put("sumMoney", sumMoney);

        //5、我的信息
        User user = userMapper.selectByOpenid(openid);
        if (Objects.equals(null,user)) {
            //用户信息
            map.put("user", 0);
        } else {
            map.put("user", user);
        }

        return new RespObj(200, "success", 1, map);
    }
}
