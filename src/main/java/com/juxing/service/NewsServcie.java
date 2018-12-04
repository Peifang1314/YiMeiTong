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

    /**
     * 存储新闻
     * @param news
     * @return
     */
    Resp newsSave(News news);

    /**
     * 获取所有新闻，展示
     * @param page 第X页
     * @return
     */
    RespObj getAllNews(Integer page);

    /**
     * 展示指定的新闻
     * @param id 新闻的id
     * @return 指定的新闻数据
     */
    RespObj getOneNews(Integer id);

    /**
     * 展示不同用户的新闻列表
     * @param flag 新闻标记码 1 店家新闻 2 渠道新闻 3 两者
     * @param page 第X页
     * @return 新闻数据
     */
    RespObj getNewsByFlag(int flag, int page);


}
