package com.juxing.service.impl;

import com.juxing.common.vo.RespObj;
import com.juxing.mapper.CodeMapper;
import com.juxing.pojo.mysqlPojo.Code;
import com.juxing.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/7 15
 * @Description:
 */

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeMapper codeMapper;

    public RespObj selectByKey(String key) {

        List<Code> codeList = codeMapper.selectByKey(key);
        if (null == codeList) {
            return RespObj.error();
        } else {
            return new RespObj(200, "msg", 1, codeMapper.selectByKey(key));
        }

    }

    public RespObj selectAll() {
        List<Code> projects = codeMapper.selectByKey("project_name");
        List<Code> designers = codeMapper.selectByKey("designer_name");

        List objs = new LinkedList();
        objs.add(projects);
        objs.add(designers);
        return new RespObj(200, "success", 1, objs);
    }

}
