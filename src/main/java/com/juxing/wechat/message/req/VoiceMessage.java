package com.juxing.wechat.message.req;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 14
 * @Description: 音频消息.
 *     原文：https://blog.csdn.net/lyq8479/article/details/8949088
 */
public class VoiceMessage extends BaseMessage{
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

}
