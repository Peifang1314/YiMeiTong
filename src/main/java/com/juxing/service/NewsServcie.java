package com.juxing.service;

import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.pojo.mysqlPojo.News;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 14
 * @Description:
 */
public interface NewsServcie {

    Resp newsSave(News news);

    RespObj getAllNews(Integer page);


}
