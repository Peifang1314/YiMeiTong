package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.vo.R;
import com.juxing.mapper.NewsMapper;
import com.juxing.pojo.News;
import com.juxing.service.NewsServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public R newsSave(News news) {
        news.setNewsId(IdUtil.getId());
        if (newsMapper.insert(news)>0){
            return new R(200,"success",1,null);
        }else {
            return new R(800,"error",0,null);
        }
    }

    @Override
    public R getAllNews() {
        List<News> datas= newsMapper.selectAll();
        if (null == datas){
            return new R(800,"error",0,null);
        }else {
            return new R(200,"success",1,datas);
        }
    }
}
