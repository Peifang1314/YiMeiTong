package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //用户注册（角色信息前端设置）
    int insert(User user);

    //号码检查
    User selectByPhone(String phone);

    //根据openid检测是否注册过
    User selectByOpenid(String openid);

    //模糊查询
    List<User> selectByText(String text);

    /**
     * 所有未审核的用户
     *
     * @return
     */
    List<User> selectUnableUser();

    /**
     * @param openList 渠道下的所有用户openId
     * @return 渠道所有未审核的用户
     */
    List<User> selectUnableUserByServ(List openList);

    /**
     * 渠道未审核的店家、我的店家
     *
     * @param openId 渠道的openId
     * @param status 店家状态
     * @param page   第X页
     * @return 渠道下不同状态的店家
     */
    List<User> selectUsersByStatus(@Param("openId") String openId, @Param("status") int status, @Param("page") int page);


    /**
     * 模糊查询渠道下的店家
     *
     * @param openList 渠道下的所有用户openId
     * @param shopName 模糊店名
     * @return
     */
    List<User> selectUsersByName(@Param("openList") List openList, @Param("shopName") String shopName);

    /**
     * 渠道-我的店家-根据电话/姓名查找
     * @param openId 渠道的openId
     * @param text 电话/姓名
     * @param page 第X页
     * @return 模糊查询（电话/姓名）的用户信息
     */
    List<User> selectByText2(@Param("openId") String openId,@Param("text") String text,@Param("page") int page);

    //更改用户状态
    int updateUserStatus(User user);

    //更改用户的信息
    int updateUser(User user);

    /**
     * 更新用户的带参数二维码地址
     *
     * @param user
     * @return
     */
    int updateUserShorturl(User user);

    /**
     * 店家审核不通过，写出原因
     * @param openId 店家的openId
     * @param notAllow 拒绝的原因
     * @return
     */
    int updateUserNotAllow(@Param("openId") String openId, @Param("notAllow") String notAllow);


    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);
}