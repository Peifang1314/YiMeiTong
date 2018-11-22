package com.juxing.wechat.message.req;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 14
 * @Description: 图片消息
 */
public class ImageMessage extends BaseMessage {

    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }


}
