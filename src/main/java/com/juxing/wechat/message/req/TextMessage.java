package com.juxing.wechat.message.req;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/13 20
 * @Description:
 *     原文：https://blog.csdn.net/lyq8479/article/details/8949088
 */
public class TextMessage extends BaseMessage{
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
