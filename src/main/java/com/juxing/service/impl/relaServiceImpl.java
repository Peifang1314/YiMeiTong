package com.juxing.service.impl;

import com.juxing.common.vo.Resp;
import com.juxing.mapper.RelationsMapper;
import com.juxing.pojo.mysqlPojo.Relations;
import com.juxing.service.RelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 12
 * @Description:
 */
@Service
public class relaServiceImpl implements RelaService {

    @Autowired
    private RelationsMapper relationsMapper;

    @Override
    public Resp getRelation(String userId) {
        Relations relation = relationsMapper.selectRelation(userId);
        if (Objects.equals(null, relation)) {
            //用户关系表不存在，进行存储
            return new Resp(404, "关系表不存在", 0);
        } else {
            //用户关系表存在
            return new Resp(200, "关系表存在", 1);
        }
    }

    @Override
    public Resp getSerRelation(String userId) {
        Relations relation = relationsMapper.selectRelation(userId);
        if (Objects.equals(null, relation.getServiceId())) {
            //渠道关系不存在，可以存储
            return new Resp(404, "渠道关系不存在", 0);
        } else {
            //用户关系表存在
            return new Resp(200, "关系表存在", 1);
        }
    }

    @Override
    public Resp updateSerRelation(Relations relation) {
        if (relationsMapper.updateServiceRelation(relation)>0){
            //更新渠道关系成功
            return Resp.ok();
        }else {
            return Resp.error();
        }
    }

    @Override
    public Resp saveFather(Relations relation) {
        String userId = relation.getUserId();
        //用户只能通过扫码进入，扫上级店家进入，存储上下级关系
        //1、新用户关系，先将关系表内自己的信息存入
        Relations relationMy = new Relations();
        relationMy.setUserId(userId);
        relationsMapper.insert(relationMy);

        //获得用户关系表
        //2、判断父级是否存在
        Relations relation1 = relationsMapper.selectRelation(userId);

        if (Objects.equals(relation1.getFatherId(), null)) {
            //2.1 没有父级，可以存储
            if (relationsMapper.updateFatherRelation(relation) > 0) {
                //存储成功
                return Resp.ok();
            } else {
                //存储失败
                return Resp.error();
            }
        } else {
            //2.2 不能存储
            return new Resp(600, "father is already", 0);
        }
    }


    @Override
    public Resp saveService(Relations relation) {
        String userId = relation.getUserId();
        //1、扫描渠道进入，存储服务关系
        Relations relationMy = new Relations();
        relationMy.setUserId(userId);
        relationsMapper.insert(relationMy);

        //2、获得用户关系表的渠道ID
        Relations relation1 = relationsMapper.selectRelation(userId);
        if (Objects.equals(relation1.getServiceId(), null)) {
            //2.1 渠道负责人为空，可以存储
            if (relationsMapper.updateServiceRelation(relation) > 0) {
                //存储成功
                return Resp.ok();
            } else {
                //存储失败
                return Resp.error();
            }
        } else {
            //2.2 不能存储
            return new Resp(601, "service is already", 0);
        }
    }
}
