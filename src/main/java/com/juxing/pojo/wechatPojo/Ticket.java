package com.juxing.pojo.wechatPojo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/8 10
 * @Description: 门票实体类，用于换取带参数二维码地址
 */
public class Ticket {
    //门票，换取二维码地址
    private String ticket;
    //有效时间
    private int expire_seconds;
    //二维码图片解析后的地址
    private String url;

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Ticket() {
    }

    public Ticket(String ticket, int expire_seconds, String url) {
        this.ticket = ticket;
        this.expire_seconds = expire_seconds;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket='" + ticket + '\'' +
                ", expire_seconds=" + expire_seconds +
                ", url='" + url + '\'' +
                '}';
    }
}
