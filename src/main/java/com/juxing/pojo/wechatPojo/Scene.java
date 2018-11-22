package com.juxing.pojo.wechatPojo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/8 10
 * @Description:
 */
public class Scene {
    // 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000
//    private int scene_id;
    // 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
    private String scene_str;

    public String getScene_str() {
        return scene_str;
    }

    public void setScene_str(String scene_str) {
        this.scene_str = scene_str;
    }

//    public void setScene_id(int scene_id) {
//        this.scene_id = scene_id;
//    }
//
//    public int getScene_id() {
//        return scene_id;
//    }


    public Scene() {
    }

    public Scene(String scene_str) {
        this.scene_str = scene_str;
    }
}
