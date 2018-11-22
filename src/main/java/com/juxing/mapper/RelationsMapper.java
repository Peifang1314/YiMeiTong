package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.Relations;

import java.util.List;

public interface RelationsMapper {

    int insert(Relations record);




    /**
     * 查询该用户的关系表
     *
     * @param openid 用户的openID
     * @return 用户的关系表
     */
    List<Relations> selectList(String openid);

    /**
     * 查找用户的子关系
     *
     * @param openid 用户的openid
     * @return 用户的子关系数据
     */
    List<Relations> selectSonList(String openid);

    /**
     * 查找该用户的上级关系表(需要关系表内的父级ID)
     * @param openid 用户的openid
     * @return
     */
    Relations selectRelation(String openid);

    /**
     *  更新用户的上下级关系
     * @param relations 上下级关系
     * @return
     */
    int updateFatherRelation(Relations relations);

    /**
     * 更新用户的渠道关系
     * @param relations 渠道关系
     * @return
     */
    int updateServiceRelation(Relations relations);

    int deleteByPrimaryKey(Integer id);

    Relations selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Relations record);
}