package com.juxing.controller;

import com.juxing.common.vo.R;
import com.juxing.pojo.News;
import com.juxing.service.NewsServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 14
 * @Description:
 */


@RestController
public class NewsController {

    @Autowired
    private NewsServcie newsServcie;

    @PostMapping("/newsSave")
    private R newsSave(News news){
        return newsServcie.newsSave(news);
    }

    @GetMapping("getAllNews")
    private R getAllNews(){
        return newsServcie.getAllNews();
    }

}
