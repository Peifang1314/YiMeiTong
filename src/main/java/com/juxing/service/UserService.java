package com.juxing.service;

import com.juxing.common.vo.R;
import com.juxing.common.vo.registResp;
import com.juxing.pojo.PhoneCode;
import com.juxing.pojo.User;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/23 14
 * @Description:
 */
public interface UserService {

    R shopSave(User user);

    registResp userSave(User user);


   R phoneCheck(String phone);

    R getUser(String text);

    R getPhoneCode(PhoneCode phoneCode);


}
