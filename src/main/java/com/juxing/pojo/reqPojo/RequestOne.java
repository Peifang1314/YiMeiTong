package com.juxing.pojo.reqPojo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/29 09
 * @Description: 携带一个参数的实体类（模糊查询、设置不通过原因等）
 */
public class RequestOne {

    private String text;

    public RequestOne() {
    }

    public RequestOne(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
