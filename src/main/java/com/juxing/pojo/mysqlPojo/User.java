package com.juxing.pojo.mysqlPojo;

import java.util.Date;

public class User {
    private Integer id;
    //用户ID
    private String userId;
    //用户微信openid
    private String userOpenid;
    //用户昵称
    private String userNickname;
    //用户头像地址
    private String userHeadimgurl;
    //用户名字
    private String userName;
    //用户店名
    private String userShopname;
    //用户电话
    private String userPhone;
    //用户商铺地址
    private String userAddress;
    //用户状态
    private Integer userStatus;
    //用户角色
    private Integer userRole;
    //店家服务热线
    private String servicePhone;
    //创建时间
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserHeadimgurl() {
        return userHeadimgurl;
    }

    public void setUserHeadimgurl(String userHeadimgurl) {
        this.userHeadimgurl = userHeadimgurl == null ? null : userHeadimgurl.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserShopname() {
        return userShopname;
    }

    public void setUserShopname(String userShopname) {
        this.userShopname = userShopname == null ? null : userShopname.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone == null ? null : servicePhone.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userOpenid='" + userOpenid + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userHeadimgurl='" + userHeadimgurl + '\'' +
                ", userName='" + userName + '\'' +
                ", userShopname='" + userShopname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userStatus=" + userStatus +
                ", userRole=" + userRole +
                ", servicePhone='" + servicePhone + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}