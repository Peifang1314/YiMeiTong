package com.juxing.pojo.wechatPojo;

import java.util.Date;
/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/12
 * @Description: 存储accessToken的实体类
 */


public class MyToken {
    private Integer id;

    //生成的令牌
    private String accessToken;
    //令牌存储时间
    private Integer expiresin;
    //创建时间
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Integer getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(Integer expiresin) {
        this.expiresin = expiresin;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}