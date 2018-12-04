package com.juxing.pojo.mysqlPojo;

public class Designer {
    private Integer id;

    private String designerId;

    private String designerName;

    private String designerPhone;

    private String createtime;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId == null ? null : designerId.trim();
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName == null ? null : designerName.trim();
    }

    public String getDesignerPhone() {
        return designerPhone;
    }

    public void setDesignerPhone(String designerPhone) {
        this.designerPhone = designerPhone == null ? null : designerPhone.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}