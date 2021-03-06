package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.Orders;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrdersMapper {
    int insert(Orders record);

    /**
     * @param orderRefer 用户openid
     * @param status     订单状态
     * @param page       第X页
     * @return 根据状态码查询用户的所有订单
     */
    List<Orders> selectByRefer(@Param("orderRefer") String orderRefer, @Param("status") int status, @Param("page") int page);

    /**
     * 输入电话或者姓名查找
     * @param openId 用户的openid
     * @param text   （姓名或电话）
     * @param page   第X页
     * @return 模糊查询出的所有订单
     */
    List<Orders> selectByText(@Param("openId") String openId, @Param("text") String text, @Param("page") int page);

    /**
     *
     * @param openId
     * @param text
     * @param page 第X页
     * @return 模糊查询出的订单
     */
    /**
     * 根据单号或者姓名查找
     * @param openId 订单创建者的openId
     * @param text 单号或者姓名
     * @param status 订单状态
     * @param page 第X页
     * @return 模糊查询，不同状态的订单
     */
    List<Orders> selectByText2(@Param("openId") String openId, @Param("text") String text,@Param("status") int status, @Param("page") int page);

    /**
     * @param cusPhone 消费者姓名
     * @param page     第X页
     * @return 消费者的所有订单（以手机号分组）
     */
    List<Orders> selectByCus(@Param("cusPhone") String cusPhone, @Param("page") int page);


    /**
     * @param openid 用户的openid
     * @param page   第X页
     * @return 该用户下的所有订单(按消费者分组)，分页显示
     */
    List<Orders> selectByOpenid(@Param("openid") String openid, @Param("page") int page);

    /**
     * @param openid 用户的openid
     * @param oid    订单号码
     * @return 根据订单号查询出的订单
     */
    Orders selectByOid(@Param("openid") String openid, @Param("oid") String oid);

    /**
     * @param openid 用户的openid
     * @return 该用户的所有订单（未分组）
     */
    List<Orders> selectAllOrders(String openid);

    /**
     * 我的模块用
     *
     * @param openid 用户的openid
     * @param status 订单状态 4 客户已付账
     * @return 返回所有顾客已付账的订单(未分组 ， 统计数据用)
     */
    List<Orders> selectAllOrders2(@Param("openid") String openid, @Param("status") int status);

    /**
     * 我的模块用，做为父级的分成金额
     *
     * @param openid 父级的openid
     * @return 做为父级的所有订单
     */
    List<Orders> selectAllOrdersByFatopenid(String openid);

    /**
     * 查找单个订单
     *
     * @param oid 订单id
     * @return 单个订单信息
     */
    Orders selectOne(String oid);

    /**
     * 查找用户的最新订单
     *
     * @param cusPhone 用户电话号码
     * @return 用户的最新订单
     */
    Orders selectNew(String cusPhone);

    /**
     * 渠道未受理的客户
     *
     * @param openId 渠道的openId
     * @return 渠道未受理的订单(orderSbuStatus为1)
     */
    List<Orders> selectByServiceOpenid(String openId);



    /**
     * 财务要审核的订单
     * @return 状态为2的订单
     */
    List<Orders> selectOrdersToFinance();

    int updateByOid(Orders order);

    /**
     * 财务人员审核订单
     * @param oid 订单的oid
     * @return 审核状态 200/800
     */
    int updateOrderByFinance(String oid);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Orders record);
}