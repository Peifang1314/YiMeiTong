package com.juxing.pojo.reqPojo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/29 09
 * @Description: 模糊查询的实体类
 */
public class SearchRequest {

    private String text;

    public SearchRequest() {
    }

    public SearchRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
