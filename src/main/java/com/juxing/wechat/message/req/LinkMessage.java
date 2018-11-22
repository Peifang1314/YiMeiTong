package com.juxing.message.req;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 14
 * @Description: 链接消息
 *     原文：https://blog.csdn.net/lyq8479/article/details/8949088
 */
public class LinkMessage extends BaseMessage{

    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
