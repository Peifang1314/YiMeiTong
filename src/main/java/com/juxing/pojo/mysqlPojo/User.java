package com.juxing.pojo.mysqlPojo;

import java.util.Date;

public class User {
    private Integer id;

    private String userId;

    private String userOpenid;

    private String userNickname;

    private String userHeadimgurl;

    private String userName;

    private String userShopname;

    private String userPhone;

    private String userAddress;

    private Integer userStatus;

    private String userNotallow;

    private Integer userRole;

    private String userShorturl;

    private String serviceOpenid;

    private String serviceId;

    private String serviceName;

    private String servicePhone;

    private Date createtime;

    private Integer isSync;

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
        this.userId = userId == null ? null : userId.trim();
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

    public String getUserNotallow() {
        return userNotallow;
    }

    public void setUserNotallow(String userNotallow) {
        this.userNotallow = userNotallow == null ? null : userNotallow.trim();
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserShorturl() {
        return userShorturl;
    }

    public void setUserShorturl(String userShorturl) {
        this.userShorturl = userShorturl == null ? null : userShorturl.trim();
    }

    public String getServiceOpenid() {
        return serviceOpenid;
    }

    public void setServiceOpenid(String serviceOpenid) {
        this.serviceOpenid = serviceOpenid == null ? null : serviceOpenid.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
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

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
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
                ", userNotallow='" + userNotallow + '\'' +
                ", userRole=" + userRole +
                ", userShorturl='" + userShorturl + '\'' +
                ", serviceOpenid='" + serviceOpenid + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                ", createtime=" + createtime +
                ", isSync=" + isSync +
                '}';
    }
}