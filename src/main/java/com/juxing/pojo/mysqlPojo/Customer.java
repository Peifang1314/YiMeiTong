package com.juxing.pojo.mysqlPojo;

import java.util.Date;

public class Customer {
    private Integer id;

    private String cusId;

    private String cusName;

    private Integer cusSex;

    private String cusPhone;

    private String cusRefer;

    private String cusReferid;

    private String cusRefername;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId == null ? null : cusId.trim();
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    public Integer getCusSex() {
        return cusSex;
    }

    public void setCusSex(Integer cusSex) {
        this.cusSex = cusSex;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone == null ? null : cusPhone.trim();
    }

    public String getCusRefer() {
        return cusRefer;
    }

    public void setCusRefer(String cusRefer) {
        this.cusRefer = cusRefer == null ? null : cusRefer.trim();
    }

    public String getCusReferid() {
        return cusReferid;
    }

    public void setCusReferid(String cusReferid) {
        this.cusReferid = cusReferid == null ? null : cusReferid.trim();
    }

    public String getCusRefername() {
        return cusRefername;
    }

    public void setCusRefername(String cusRefername) {
        this.cusRefername = cusRefername == null ? null : cusRefername.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusSex=" + cusSex +
                ", cusPhone='" + cusPhone + '\'' +
                ", cusRefer='" + cusRefer + '\'' +
                ", cusReferid='" + cusReferid + '\'' +
                ", cusRefername='" + cusRefername + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}