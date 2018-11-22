package com.juxing.pojo.mysqlPojo;

import java.util.Date;
/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/29 09
 * @Description: 首页轮播图的实体类
 */
public class Headimg {
    private Integer id;

    private String src;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Headimg{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}