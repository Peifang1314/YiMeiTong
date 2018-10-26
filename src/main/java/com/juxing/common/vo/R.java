package com.juxing.common.vo;

/*统一json结果类 非查询*/
public class R {
    private int status;
    private String msg;
    private int submitStatus;
    private Object data;

    public R() {
    }

    public R(int status, String msg, int submintStatus, Object data) {
        this.status = status;
        this.msg = msg;
        this.submitStatus = submintStatus;
        this.data = data;
    }

    public R(int status, String msg, int submintStatus) {
        this.status = status;
        this.msg = msg;
        this.submitStatus = submintStatus;
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

    public int getSubmintStatus() {
        return submitStatus;
    }

    public void setSubmintStatus(int submintStatus) {
        this.submitStatus = submintStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static R ok() {
        return new R(200, "success", 1, null);
    }
    public static R error(){
        return new R(800,"error",0,null);
    }

}
