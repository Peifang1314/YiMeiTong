package com.juxing.controller.worker;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.pojo.reqPojo.RequestList;
import com.juxing.pojo.reqPojo.RequestOne;
import com.juxing.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/26 13
 * @Description: 渠道首页的controller层
 */
@RestController
@RequestMapping("/api")
public class WorkerController {

    @Autowired
    private WorkerService workerService;


    /**
     * 渠道版的首页接口
     *
     * @param request 请求的实体类，渠道的openId
     * @return 首页的数据
     */
    @RequestMapping("/workerIndex")
    public RespObj serIndex(@RequestBody RequestOne request) {
        String openId = request.getText();
        Map map = workerService.workerIndex(openId);
        return new RespObj(200, "success", 1, map);
    }

    /**
     * 未受理的预约顾客
     *
     * @param request 请求实体类，渠道的openId
     * @return 未受理的顾客订单
     */
    @RequestMapping("/getMyReCus")
    public RespObj getMyReCus(@RequestBody RequestOne request) {
        String openId = request.getText();
        return workerService.myReOrders(openId);
    }

    /**
     * 渠道-我的店家
     *
     * @param request openId 渠道的openId status 店家的状态 page 第X页
     * @return
     */
    @RequestMapping("/getShopsOfMy")
    public RespObj getShopsOfMy(@RequestBody RequestList request) {
        String openId = request.getText();
        int status = request.getNum();
        int page = request.getPage();
        return workerService.getShopsOfMy(openId, status, page);
    }

    /**
     *
     *
     * @return
     */
    /**
     * 渠道-我的店家-输入电话或者店名查找
     * @param request openId 渠道的openId, text 电话/姓名  page 第X页
     * @return 店铺的信息（店名、订单总返款金额）
     */
    @RequestMapping("/getMyShopsByText")
    public RespObj getMyShopsByText(@RequestBody RequestList request) {
        String openId = request.getText();
        String text = request.getText1();
        int page = request.getPage();
        return workerService.getMyShopsByText(openId, text, page);
    }


    /**
     * 修改订单
     *
     * @param request 请求实体类，订单的ID
     * @return 单一的一条订单
     */
    @RequestMapping("/getOneOrder")
    public RespObj getOneOrder(@RequestBody RequestOne request) {
        String oid = request.getText();
        return workerService.getOneOrder(oid);
    }

    /**
     * 订单审核功能
     *
     * @param order 要审核的订单
     * @return 审核状态 200/800
     */
    @RequestMapping("/getOrderPass")
    public Resp getOrderPass(@RequestBody Orders order) {
        return workerService.getOrderPass(order);
    }

}
