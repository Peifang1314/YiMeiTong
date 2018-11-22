package com.juxing.pojo.mysqlPojo;

import java.util.Date;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/21 10
 * @Description: 订单的实体类
 */

public class Orders {
    //主键
    private Integer id;
    //订单ID
    private String orderId;
    //订单的项目
    private String orderProject;
    //订单的设计师
    private String orderDesigner;
    //订单金额
    private String orderMoney;
    //订单创建者的openID
    private String orderRefer;
    //创建者的ID
    private String orderUserid;
    //创建者的姓名
    private String orderUsername;
    //创建者父级的openID
    private String orderFatherOpenid;
    //创建者父级的ID
    private String orderFatherId;
    //创建者父级的姓名
    private String orderFatherName;
    //创建者渠道的openID
    private String orderServiceOpenid;
    //创建者渠道的ID
    private String orderServiceId;
    //创建者渠道的姓名
    private String orderServiceName;
    //订单的店名
    private String orderShopname;
    //订单的返款金额
    private String orderDismoney;
    //订单父级的的返款金额
    private String orderFatherDismoney;
    //订单的创建时间
    private String orderCometime;
    //顾客的ID
    private String cusId;
    //顾客的姓名
    private String cusName;
    //顾客的性别
    private Integer cusSex;
    //顾客的电话
    private String cusPhone;
    //订单的创建时间
    private Date createtime;
    //订单的付款时间
    private String orderPaytime;
    //订单的返款时间
    private String orderDistime;
    //订单的提交者 1-店家 2-渠道
    private Integer orderSubstatus;
    //订单的状态
    private Integer orderStatus;
    //订单的备注信息
    private String orderNotes;
    //是否同步
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
                ", isSync=" + isSync +
                '}';
    }
}