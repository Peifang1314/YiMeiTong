package com.juxing.controller;

import com.juxing.common.vo.RespObj;
import com.juxing.pojo.reqPojo.SearchRequest;
import com.juxing.service.HeadimgService;
import com.juxing.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/12 14
 * @Description: 首页的controller层
 *
 */
@RestController
@RequestMapping("/api")
public class indexController {

    @Autowired
    private HeadimgService headimgService;

    @Autowired
    private IndexService indexService;


    /**
     * 首页的接口（轮播图、返款、下级店铺、用户信息）
     * @param request 查询实体类（含用户的openid）
     * @return
     */
    @RequestMapping("/getIndex")
    public RespObj getIndex(@RequestBody SearchRequest request){
        return indexService.getIndex(request.getText());
    }

    /**
     * 我的模块数据（个人信息，收益，客户，总订单，下级店家）
     * @param request 查询实体类（含用户的openid）
     * @return
     */
    @RequestMapping("/getMyTotalInfo")
    public RespObj getMyInfo(@RequestBody SearchRequest request){
        return indexService.getMyInfo(request.getText());
    }




//------------------------用不到--------------------------------------------------
    /**
     * 首页轮播图
     * @return
     */
    @RequestMapping("/getImgs")
    public RespObj getImgs(){
        return headimgService.getHeadimgs();
    }

}
