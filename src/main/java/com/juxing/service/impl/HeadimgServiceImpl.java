package com.juxing.service.impl;

import com.juxing.common.vo.RespObj;
import com.juxing.mapper.HeadimgMapper;
import com.juxing.service.HeadimgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/12 14
 * @Description:
 */
@Service
public class HeadimgServiceImpl implements HeadimgService {

    @Autowired
    private HeadimgMapper headimgMapper;
    @Override
    public RespObj getHeadimgs() {
        List imgs= headimgMapper.selectAllByTime();
        if(null==imgs){
            return RespObj.error();
        }else {
            return new RespObj(200,"success",1,imgs);
        }
    }
}
