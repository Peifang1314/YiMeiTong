package com.juxing.controller.worker;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.Orders;
import com.juxing.pojo.reqPojo.RequestOne;
import com.juxing.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * 修改订单
     *
     * @param request 请求实体类，订单的ID
     * @return
     */
    @RequestMapping("/getOneOrder")
    public RespObj getOneOrder(@RequestBody RequestOne request) {
        String oid = request.getText();
        return workerService.getOneOrder(oid);
    }

    /**
     * 订单审核
     * @param order 要审核的订单
     * @return
     */
    @RequestMapping("/getOrderPass")
    public Resp getOrderPass(@RequestBody Orders order) {
        return workerService.getOrderPass(order);
    }

}
