package com.juxing.service.impl;

import com.juxing.common.vo.RespObj;
import com.juxing.mapper.DesignerMapper;
import com.juxing.pojo.mysqlPojo.Designer;
import com.juxing.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/26 16
 * @Description:
 */
@Service
public class DesignerServiceImpl implements DesignerService {

    @Autowired
    private DesignerMapper designerMapper;

    @Override
    public RespObj getDesigners() {
        List<Designer> designers = designerMapper.selectAllDesigners();
        if (Objects.equals(null,designers)){
            return new RespObj(800,"没有找到设计师",0,null);
        }else {
            return new RespObj(200,"success",1,designers);
        }
    }
}
