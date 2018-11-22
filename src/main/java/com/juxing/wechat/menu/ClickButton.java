package com.juxing.wechat.menu;

/**
 * @description: click类型的按钮
 * @author: Mr.lsf
 * @create: 2018-11-19 21:11
 **/
public class ClickButton extends Button{
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
