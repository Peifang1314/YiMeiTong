package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    /**
     * @param page 第X页
     * @return 查询所有的新闻，分页
     */
    List<News> selectAll(@Param("page") int page);

    //首页的新闻数据（最新3条）
    List<News> selectThree();

    /**
     * @return 所有新闻
     */
    List<News> selectAllNews();


    int updateByPrimaryKey(News record);
}