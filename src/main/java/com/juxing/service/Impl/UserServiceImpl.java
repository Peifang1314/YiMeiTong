package com.juxing.service.impl;

import com.juxing.common.util.IdUtil;
import com.juxing.common.vo.R;
import com.juxing.common.vo.registResp;
import com.juxing.mapper.PhoneCodeMapper;
import com.juxing.mapper.UserMapper;
import com.juxing.miaodiyun.httpApiDemo.IndustrySMS;
import com.juxing.miaodiyun.httpApiDemo.common.RandUtil;
import com.juxing.pojo.PhoneCode;
import com.juxing.pojo.User;
import com.juxing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/10/23 14
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PhoneCodeMapper phoneCodeMapper;




    /**
     * 渠道负责人注册
     *
     * @param user
     * @return
     */
    @Override
    public registResp userSave(User user) {
        String userName = user.getUserName();
        String userPhone = user.getUserPhone();
        Integer userRole = user.getUserRole();
        //判断用户名是否为空
        if (null == userName) {
            return new registResp(800, "用户名不能为空", 0);
        } else {
            if (userPhone.length() != 11) {
                return new registResp(802, "号码长度不够", 0);
            } else {
                User user1 = userMapper.selectByPhone(userPhone);
                //判断号码是否被使用，找到用户说明号码已被注册
                if (null != user1) {
                    return new registResp(801, "手机号已被注册", 0);
                } else {
                    //进行注册
                    user.setUserId(IdUtil.getId());
                    user.setUserRole(userRole);
                    if (userMapper.insert(user) > 0) {
                        return new registResp(200, "success", 1);
                    } else {
                        return new registResp(803, "服务器繁忙注册失败", 0);
                    }
                }
            }
        }
    }

    /**
     * 验证码发送
     * @param phoneCode
     * @return
     */
    @Override
    public R getPhoneCode(PhoneCode phoneCode) {
        String phone=phoneCode.getPhone();
        int code =RandUtil.getRandNum();

        String smsContent="【巨星集团】您的验证码为" + code + "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
        IndustrySMS.execute(phone, smsContent);

        phoneCode.setCode(code);
        if(phoneCodeMapper.insert(phoneCode)>0){
            return new R(200,"success",1,code);
        }else {
            return new R(800,"error",0);
        }
    }


    @Override
    public R phoneCheck(String phone) {
        User user = userMapper.selectByPhone(phone);
        if (null == user) {
            return new R(200, "号码已被使用", 1, null);
        } else {
            return new R(800, "号码不可用", 0, null);
        }
    }


    @Override
    public R getUser(String text) {
        List<User> datas = userMapper.selectByText(text);
        System.out.println(datas);
        if (null == datas) {
            return new R(800, "error", 0, null);
        } else {
            return new R(200, "success", 1, datas);
        }
    }


    /**
     * 店家注册
     *
     * @param user
     * @return
     */
    @Override
    public R shopSave(User user) {

        user.setUserId(IdUtil.getId());

        if (userMapper.shopInsert(user) > 0) {
            return new R(200, "success", 1, null);
        } else {
            return new R(800, "error", 0, null);
        }
    }

}
