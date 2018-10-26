package com.juxing.common.vo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/26 14
 * @Description: 没有返回数据的返回对象
 */
public class registResp {
    private int status;
    private String msg;
    private int submitStatus;

    public registResp() {
    }

    public registResp(int status, String msg, int submitStatus) {
        this.status = status;
        this.msg = msg;
        this.submitStatus = submitStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    @Override
    public String toString() {
        return "registResp{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", submitStatus=" + submitStatus +
                '}';
    }
}
