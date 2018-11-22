package com.juxing.common.vo;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/26 14
 * @Description: 有返回数据的返回对象
 */
public class RespObj {
    private int status;
    private String msg;
    private int submitStatus;
    private Object obj;

    public RespObj() {
    }

    public RespObj(int status, String msg, int submintStatus, Object obj) {
        this.status = status;
        this.msg = msg;
        this.submitStatus = submintStatus;
        this.obj = obj;
    }

    public RespObj(int status, String msg, int submintStatus) {
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static RespObj ok() {
        return new RespObj(200, "success", 1, null);
    }
    public static RespObj error(){
        return new RespObj(800,"error",0,null);
    }

}
