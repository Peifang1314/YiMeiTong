package com.juxing.pojo.mysqlPojo;

import java.util.Date;

public class Orders {
    private Integer id;

    private String orderId;

    private String orderProject;

    private String orderDesigner;

    private String orderMoney;

    private String orderRefer;

    private String orderUserid;

    private String orderUsername;

    private String orderFatheropenid;

    private String orderFatherid;

    private String orderFathername;

    private String orderShopname;

    private String orderDismoney;

    private String orderFatherdismoney;

    private String orderCometime;

    private String cusId;

    private String cusName;

    private Integer cusSex;

    private String cusPhone;

    private Date createtime;

    private Integer orderStatus;

    private Integer isSync;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderProject() {
        return orderProject;
    }

    public void setOrderProject(String orderProject) {
        this.orderProject = orderProject == null ? null : orderProject.trim();
    }

    public String getOrderDesigner() {
        return orderDesigner;
    }

    public void setOrderDesigner(String orderDesigner) {
        this.orderDesigner = orderDesigner == null ? null : orderDesigner.trim();
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney == null ? null : orderMoney.trim();
    }

    public String getOrderRefer() {
        return orderRefer;
    }

    public void setOrderRefer(String orderRefer) {
        this.orderRefer = orderRefer == null ? null : orderRefer.trim();
    }

    public String getOrderUserid() {
        return orderUserid;
    }

    public void setOrderUserid(String orderUserid) {
        this.orderUserid = orderUserid == null ? null : orderUserid.trim();
    }

    public String getOrderUsername() {
        return orderUsername;
    }

    public void setOrderUsername(String orderUsername) {
        this.orderUsername = orderUsername == null ? null : orderUsername.trim();
    }

    public String getOrderFatheropenid() {
        return orderFatheropenid;
    }

    public void setOrderFatheropenid(String orderFatheropenid) {
        this.orderFatheropenid = orderFatheropenid == null ? null : orderFatheropenid.trim();
    }

    public String getOrderFatherid() {
        return orderFatherid;
    }

    public void setOrderFatherid(String orderFatherid) {
        this.orderFatherid = orderFatherid == null ? null : orderFatherid.trim();
    }

    public String getOrderFathername() {
        return orderFathername;
    }

    public void setOrderFathername(String orderFathername) {
        this.orderFathername = orderFathername == null ? null : orderFathername.trim();
    }

    public String getOrderShopname() {
        return orderShopname;
    }

    public void setOrderShopname(String orderShopname) {
        this.orderShopname = orderShopname == null ? null : orderShopname.trim();
    }

    public String getOrderDismoney() {
        return orderDismoney;
    }

    public void setOrderDismoney(String orderDismoney) {
        this.orderDismoney = orderDismoney == null ? null : orderDismoney.trim();
    }

    public String getOrderFatherdismoney() {
        return orderFatherdismoney;
    }

    public void setOrderFatherdismoney(String orderFatherdismoney) {
        this.orderFatherdismoney = orderFatherdismoney == null ? null : orderFatherdismoney.trim();
    }

    public String getOrderCometime() {
        return orderCometime;
    }

    public void setOrderCometime(String orderCometime) {
        this.orderCometime = orderCometime;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", orderProject='" + orderProject + '\'' +
                ", orderDesigner='" + orderDesigner + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", orderRefer='" + orderRefer + '\'' +
                ", orderUserid='" + orderUserid + '\'' +
                ", orderUsername='" + orderUsername + '\'' +
                ", orderFatheropenid='" + orderFatheropenid + '\'' +
                ", orderFatherid='" + orderFatherid + '\'' +
                ", orderFathername='" + orderFathername + '\'' +
                ", orderShopname='" + orderShopname + '\'' +
                ", orderDismoney='" + orderDismoney + '\'' +
                ", orderFatherdismoney='" + orderFatherdismoney + '\'' +
                ", orderCometime='" + orderCometime + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusSex=" + cusSex +
                ", cusPhone='" + cusPhone + '\'' +
                ", createtime=" + createtime +
                ", orderStatus=" + orderStatus +
                ", isSync=" + isSync +
                '}';
    }
}