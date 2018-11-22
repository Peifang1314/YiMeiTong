package com.juxing.message.resp;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 14
 * @Description: 音乐消息
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }

}
