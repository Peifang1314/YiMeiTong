package com.juxing.message.resp;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 14
 * @Description: 文本消息
 *     原文：https://blog.csdn.net/lyq8479/article/details/8949088
 */
public class TextMessage extends BaseMessage{

        // 回复的消息内容
        private String Content;

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

}
