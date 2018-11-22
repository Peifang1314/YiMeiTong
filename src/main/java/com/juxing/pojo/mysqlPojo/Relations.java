package com.juxing.pojo.mysqlPojo;

import java.util.Date;

public class Relations {
    private Integer id;

    private String userId;

    private String fatherId;

    private String serviceId;

    private Date createtime;

    private Date modifytime;

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

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId == null ? null : fatherId.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    @Override
    public String toString() {
        return "Relations{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", fatherId='" + fatherId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                '}';
    }


}