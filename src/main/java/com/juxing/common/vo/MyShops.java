package com.juxing.common.vo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/12/3 14
 * @Description: 我的下级店铺实体类
 */
public class MyShops {
    // 店铺人员的openId
    private String openId;
    // 店铺名
    private String shopName;
    // 店铺订单总金额
    private int num;

    public MyShops() {
    }

    public MyShops(String openId, String shopName, int num) {
        this.openId = openId;
        this.shopName = shopName;
        this.num = num;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
