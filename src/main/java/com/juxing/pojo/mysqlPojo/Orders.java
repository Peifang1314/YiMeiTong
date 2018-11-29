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

    private String orderFatherOpenid;

    private String orderFatherId;

    private String orderFatherName;

    private String orderServiceOpenid;

    private String orderServiceId;

    private String orderServiceName;

    private String orderShopname;

    private String orderDismoney;

    private String orderFatherDismoney;

    private String orderCometime;

    private String cusId;

    private String cusName;

    private Integer cusSex;

    private String cusPhone;

    private Date createtime;

    private String orderPaytime;

    private String orderDistime;

    private Integer orderSubstatus;

    private Integer orderStatus;

    private String orderNotes;
    //渠道备注
    private String orderSecondNotes;

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

    public String getOrderFatherOpenid() {
        return orderFatherOpenid;
    }

    public void setOrderFatherOpenid(String orderFatherOpenid) {
        this.orderFatherOpenid = orderFatherOpenid == null ? null : orderFatherOpenid.trim();
    }

    public String getOrderFatherId() {
        return orderFatherId;
    }

    public void setOrderFatherId(String orderFatherId) {
        this.orderFatherId = orderFatherId == null ? null : orderFatherId.trim();
    }

    public String getOrderFatherName() {
        return orderFatherName;
    }

    public void setOrderFatherName(String orderFatherName) {
        this.orderFatherName = orderFatherName == null ? null : orderFatherName.trim();
    }

    public String getOrderServiceOpenid() {
        return orderServiceOpenid;
    }

    public void setOrderServiceOpenid(String orderServiceOpenid) {
        this.orderServiceOpenid = orderServiceOpenid == null ? null : orderServiceOpenid.trim();
    }

    public String getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(String orderServiceId) {
        this.orderServiceId = orderServiceId == null ? null : orderServiceId.trim();
    }

    public String getOrderServiceName() {
        return orderServiceName;
    }

    public void setOrderServiceName(String orderServiceName) {
        this.orderServiceName = orderServiceName == null ? null : orderServiceName.trim();
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

    public String getOrderFatherDismoney() {
        return orderFatherDismoney;
    }

    public void setOrderFatherDismoney(String orderFatherDismoney) {
        this.orderFatherDismoney = orderFatherDismoney == null ? null : orderFatherDismoney.trim();
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

    public String getOrderPaytime() {
        return orderPaytime;
    }

    public void setOrderPaytime(String orderPaytime) {
        this.orderPaytime = orderPaytime;
    }

    public String getOrderDistime() {
        return orderDistime;
    }

    public void setOrderDistime(String orderDistime) {
        this.orderDistime = orderDistime;
    }

    public Integer getOrderSubstatus() {
        return orderSubstatus;
    }

    public void setOrderSubstatus(Integer orderSubstatus) {
        this.orderSubstatus = orderSubstatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes == null ? null : orderNotes.trim();
    }

    public String getOrderSecondNotes() {
        return orderSecondNotes;
    }

    public void setOrderSecondNotes(String orderSecondNotes) {
        this.orderSecondNotes = orderSecondNotes == null ? null : orderSecondNotes.trim();
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
                ", orderFatherOpenid='" + orderFatherOpenid + '\'' +
                ", orderFatherId='" + orderFatherId + '\'' +
                ", orderFatherName='" + orderFatherName + '\'' +
                ", orderServiceOpenid='" + orderServiceOpenid + '\'' +
                ", orderServiceId='" + orderServiceId + '\'' +
                ", orderServiceName='" + orderServiceName + '\'' +
                ", orderShopname='" + orderShopname + '\'' +
                ", orderDismoney='" + orderDismoney + '\'' +
                ", orderFatherDismoney='" + orderFatherDismoney + '\'' +
                ", orderCometime='" + orderCometime + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusSex=" + cusSex +
                ", cusPhone='" + cusPhone + '\'' +
                ", createtime=" + createtime +
                ", orderPaytime='" + orderPaytime + '\'' +
                ", orderDistime='" + orderDistime + '\'' +
                ", orderSubstatus=" + orderSubstatus +
                ", orderStatus=" + orderStatus +
                ", orderNotes='" + orderNotes + '\'' +
                ", orderSecondNotes='" + orderSecondNotes + '\'' +
                ", isSync=" + isSync +
                '}';
    }
}