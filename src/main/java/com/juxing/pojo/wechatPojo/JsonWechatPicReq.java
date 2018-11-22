package com.juxing.pojo.wechatPojo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/8 10
 * @Description: 带参数二维码请求对象
 */
public class JsonWechatPicReq {

    // 该二维码有效时间，以秒为单位
    private long expire_seconds;
    // 二维码类型，QR_SCENE为临时的整型参数值
    // QR_STR_SCENE为临时的字符串参数值
    // QR_LIMIT_SCENE为永久的整型参数值
    // QR_LIMIT_STR_SCENE为永久的字符串参数值
    private String action_name;
    // 二维码详细信息
    private Action_info action_info;

    public void setExpire_seconds(long expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public long getExpire_seconds() {
        return expire_seconds;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_info(Action_info action_info) {
        this.action_info = action_info;
    }

    public Action_info getAction_info() {
        return action_info;
    }

    public JsonWechatPicReq() {
    }

    public JsonWechatPicReq(long expire_seconds, String action_name, Action_info action_info) {
        this.expire_seconds = expire_seconds;
        this.action_name = action_name;
        this.action_info = action_info;
    }
}
