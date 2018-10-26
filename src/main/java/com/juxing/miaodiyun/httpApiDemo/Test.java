package com.juxing.miaodiyun.httpApiDemo;

import com.juxing.miaodiyun.httpApiDemo.common.RandUtil;

public class Test {
    static String smsContent = "【巨星集团】您的验证码为" + RandUtil.getRandNum() + "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
    static String phone = "15729058895";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 获取开发者账号信息
        // AccountInfo.execute();

        // 验证码通知短信接口
        IndustrySMS.execute(phone, smsContent);

        // 会员营销短信接口
        // AffMarkSMS.execute();

        // 语音验证码
        // VoiceCode.execute();

    }
}
