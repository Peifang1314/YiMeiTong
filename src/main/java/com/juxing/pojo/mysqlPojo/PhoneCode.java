package com.juxing.pojo.mysqlPojo;

import java.util.Date;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/29 09
 * @Description: 发送验证码的实体类
 */

public class PhoneCode {
    private Integer id;

    private String phone;

    private Integer code;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code=" + code +
                ", createtime=" + createtime +
                '}';
    }
}