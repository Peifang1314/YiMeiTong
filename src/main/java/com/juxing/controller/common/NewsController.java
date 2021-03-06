package com.juxing.controller.common;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.News;
import com.juxing.pojo.reqPojo.RequestList;
import com.juxing.pojo.reqPojo.RequestOne;
import com.juxing.service.NewsServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 14
 * @Description: 新闻中心
 */


@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsServcie newsServcie;

    @PostMapping("/newsSave")
    public Resp newsSave(News news) {
        return newsServcie.newsSave(news);
    }

    /**
     * 获得新闻列表
     *
     * @return
     */
    @GetMapping("/getAllNews")
    public RespObj getAllNews(HttpServletRequest request) {
        Integer page = Integer.valueOf(request.getParameter("page"));
        System.out.println("page:" + page);
        return newsServcie.getAllNews(page);
    }

    @RequestMapping("/getOneNews")
    public RespObj getOneNews(@RequestBody RequestOne request) {
        int id = Integer.valueOf(request.getText());
        return newsServcie.getOneNews(id);
    }

    @RequestMapping("/getNewsByStatus")
    public RespObj getNewsByStatus(@RequestBody RequestList request) {
        int flag = request.getNum();
        int page = request.getPage();

        return newsServcie.getNewsByFlag(flag, page);
    }


}
