package com.juxing.service;

import com.juxing.common.vo.Resp;
import com.juxing.pojo.mysqlPojo.Relations;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/15 12
 * @Description: 上下级关系的service层
 */
public interface RelaService {

    /**
     * 提前判断用户关系是否存在
     * @param userId 用户的openid
     * @return
     */
    Resp getRelation(String userId);

    /**
     * 提前判断用户渠道关系是否存在
     * @param userId 用户的openid
     * @return
     */
    Resp getSerRelation(String userId);

    /**
     * 用户关系存在，更新渠道关系
     * @param relation
     * @return
     */
    Resp updateSerRelation(Relations relation);


    /**
     * 存储父级，当前用户为子级
     *
     * @param relation
     * @return
     */
    Resp saveFather(Relations relation);

    /**
     * 存储渠道负责人
     *
     * @param relation
     * @return
     */
    Resp saveService(Relations relation);




}
