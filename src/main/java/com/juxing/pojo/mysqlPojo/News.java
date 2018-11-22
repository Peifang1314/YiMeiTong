package com.juxing.pojo.mysqlPojo;

import java.util.Date;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/29 09
 * @Description: 新闻中心的实体类
 */

public class News {
    private Integer id;

    private String newsId;

    private String newsTitle;

    private String newsCreatetime;

    private String newsContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsCreatetime() {
        return newsCreatetime;
    }

    public void setNewsCreatetime(String newsCreatetime) {
        this.newsCreatetime = newsCreatetime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsId='" + newsId + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsCreatetime='" + newsCreatetime + '\'' +
                ", newsContent='" + newsContent + '\'' +
                '}';
    }
}