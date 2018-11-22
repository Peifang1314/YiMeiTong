package com.juxing.service;

import com.juxing.common.vo.RespObj;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/7 15
 * @Description:
 */
public interface CodeService {

    /**
     *
     * @param key 键的值
     * @return code表中对应的值
     */
    RespObj selectByKey(String key);

    /**
     * 咨询项目和设计师
     * @return
     */
    RespObj selectAll();

}
