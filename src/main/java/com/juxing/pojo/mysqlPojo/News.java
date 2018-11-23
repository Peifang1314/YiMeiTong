package com.juxing.pojo.mysqlPojo;

import java.util.Date;

public class News {
    private Integer id;

    private String newsId;

    private String newsTitle;

    private String newsContent;

    private String newsContentHtml;

    private Date newsCreatetime;

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
        this.newsId = newsId == null ? null : newsId.trim();
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }

    public String getNewsContentHtml() {
        return newsContentHtml;
    }

    public void setNewsContentHtml(String newsContentHtml) {
        this.newsContentHtml = newsContentHtml == null ? null : newsContentHtml.trim();
    }

    public Date getNewsCreatetime() {
        return newsCreatetime;
    }

    public void setNewsCreatetime(Date newsCreatetime) {
        this.newsCreatetime = newsCreatetime;
    }
}