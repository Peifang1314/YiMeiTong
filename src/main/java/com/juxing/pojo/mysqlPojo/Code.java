package com.juxing.pojo.mysqlPojo;
/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/29 09
 * @Description: 状态码的实体类
 */
public class Code {
    private Integer id;
    //状态键
    private String codeKey;
    //状态描述
    private String codeName;
    //状态的值
    private Integer codeValue;
    //状态类型
    private String codeType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey == null ? null : codeKey.trim();
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public Integer getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Integer codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", codeKey='" + codeKey + '\'' +
                ", codeName='" + codeName + '\'' +
                ", codeValue=" + codeValue +
                ", codeType='" + codeType + '\'' +
                '}';
    }
}