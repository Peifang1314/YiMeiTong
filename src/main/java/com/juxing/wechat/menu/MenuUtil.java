package com.juxing.wechat.menu;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.util.CommonUtil;
import com.juxing.pojo.wechatPojo.App;
import com.juxing.pojo.wechatPojo.Token;

/**
 * @description:
 * @author: Mr.lsf
 * @create: 2018-11-19 21:21
 **/
public class MenuUtil {

    public static String createButton() {
//        ClickButton btn1 = new ClickButton();
//        btn1.setName("巨星集团");
//        btn1.setType("view");
//        //主页事件
//        btn1.setKey("V1001_HOME");

        ViewButton btn1 = new ViewButton();
        btn1.setName("巨星集团");
        btn1.setType("view");
        btn1.setUrl("https://m.superstar.vc?a=1");


        ClickButton btn2 = new ClickButton();
        btn2.setName("我的二维码");
        btn2.setType("click");
        //我的推广二维码
        btn2.setKey("V1002_MyQRCode");


        ViewButton btn31 = new ViewButton();
        btn31.setName("店家注册");
        btn31.setType("view");
        btn31.setUrl("https://m.superstar.vc");

        ViewButton btn32 = new ViewButton();
        btn32.setName("渠道注册");
        btn32.setType("view");
        btn32.setUrl("https://m.superstar.vc/flinto");

        //复合按钮包含2个click类型的按钮
        ComplexButton btn3 = new ComplexButton();
        btn3.setName("医美通");
        btn3.setSub_button(new Button[]{btn31, btn32});

        //创建菜单对象
        Menu menu = new Menu();
        menu.setButton(new Button[]{btn1, btn2, btn3});

        String jsonMenu = JSONObject.toJSONString(menu);
        return jsonMenu;
    }

    /**
     * 生成自定义菜单
     * @return
     */
    public static String createMenu() {
        Token token = CommonUtil.getToken(App.APPID, App.APPSECRET);
        System.out.println("token:"+token);
        String menuCreateUrl = App.CREATE_MENU.replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = CommonUtil.httpsRequest(menuCreateUrl, "POST", MenuUtil.createButton());
        System.out.println("jsonObjecct"+jsonObject);
        String str = jsonObject.toString();
        return str;
    }
}
