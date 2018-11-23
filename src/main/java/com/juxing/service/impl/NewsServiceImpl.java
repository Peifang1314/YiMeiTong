package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.vo.Resp;
import com.juxing.common.vo.RespObj;
import com.juxing.mapper.NewsMapper;
import com.juxing.pojo.mysqlPojo.News;
import com.juxing.service.NewsServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 14
 * @Description:
 */

@Service
public class NewsServiceImpl implements NewsServcie {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public Resp newsSave(News news) {
        news.setNewsId(IdUtil.getId());
        if (newsMapper.insert(news)>0){
            return new Resp(200,"success",1);
        }else {
            return new Resp(800,"error",0);
        }
    }

    @Override
    public RespObj getAllNews(Integer page) {
        List<News> datas= newsMapper.selectAll(page);
        System.out.println("impl--page:"+page);
        if (null == datas){
            return new RespObj(800,"error",0,null);
        }else {
            return new RespObj(200,"success",1,datas);
        }
    }

    @Override
    public RespObj getOneNews(Integer id) {
        News news = newsMapper.selectByPrimaryKey(id);
        if (Objects.equals(null,news)){
            return RespObj.error();
        }else {
            return new RespObj(200,"新闻",1,news);
        }
    }
}
