package com.juxing.pojo.reqPojo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/9 10
 * @Description:
 * 多个参数的请求实体类（查询结果需分页）
 */
public class RequestList {

    //查询字段1
    private String text;
    //查询字段2
    private String text1;

    //int类型数据1（如status）
    private int num;
    //int类型数据2（如page）
    private int page;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
