package com.juxing.service.impl;

import com.juxing.common.util.WechatMessageUtil;
import com.juxing.mapper.RelationsMapper;
import com.juxing.pojo.mysqlPojo.Relations;
import com.juxing.service.WechatCoreService;
import com.juxing.wechat.message.resp.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/21 17
 * @Description:
 */
@Service
public class WechatCoreServiceImpl implements WechatCoreService {

    @Autowired
    private RelationsMapper relationsMapper;


    @Override
    public String processRequest(HttpServletRequest request) {
        //xml格式的消息数据
        String respXml = null;

        //默认返回的文本消息内容
        String respContent = "未知的消息类型!";

        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        //开发者微信号
        String toUserName = map.get("ToUserName");
        //发送方的openid
        String fromUserName = map.get("FromUserName");
        //消息类型
        String msgType = map.get("MsgType");
        //事件类型
        String event = map.get("Event");
        //携带的参数（上级的openid）,点击事件的参数
        String eventKey = map.get("EventKey");

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);

        // 普通消息
        if ("text".equals(msgType)) { // 文本消息
            // todo 处理文本消息
            respContent = "文本消息";
        } else if ("image".equals(msgType)) { // 图片消息
            // todo 处理图片消息
            respContent = "图片消息";
        } else if ("voice".equals(msgType)) { //语音消息
            // todo 处理语音消息
            respContent = "语音消息";
        } else if ("video".equals(msgType)) { // 视频消息
            // todo 处理视频消息
            respContent = "视频消息";
        } else if ("shortvideo".equals(msgType)) { // 小视频消息
            // todo 处理小视频消息
            respContent = "小视频消息";
        } else if ("location".equals(msgType)) { // 地理位置消息
            // todo 处理地理位置消息
            respContent = "地理位置消息";
        } else if ("link".equals(msgType)) { // 链接消息
            // todo 处理链接消息
            respContent = "链接消息";
        } else if ("event".equals(msgType)) { // 事件消息
            // todo 处理事件
            if ("subscribe".equals(event)) {
                // 订阅事件 或 未关注扫描二维码事件
                System.out.println("未关注的扫码进入");
                // 1、判断二维码来源（1--店家、2--渠道负责人）
                // 截取携带的参数 trueKey
                String trueKey = eventKey.substring(8);
                char a = trueKey.charAt(0);
                String first = String.valueOf(a);
                if (Objects.equals("1", first)) {
                    //来源是店家
                    Relations relation = new Relations();
                    //设置上下级关系
                    relation.setUserId(fromUserName);
                    relation.setFatherId(eventKey.substring(9));

                    //1、判断用户关系是否存在
                    Relations isRelation = relationsMapper.selectRelation(fromUserName);
                    if (Objects.equals(null, isRelation)) {
                        //1.1 不存在，就存储
                        if (relationsMapper.insert(relation) > 0) {
                            respContent = "医美通系统：http://m.superstar.vc";
                        } else {
                            respContent = "用户上下级关系未形成，医美通：http://m.superstar.vc";
                        }
                    } else {
                        // 1.2 关系存在，返回注册网址
                        respContent = "医美通系统：http://m.superstar.vc";
                    }
                } else if (Objects.equals("2", first)) {
                    //来源是渠道，渠道关系形成
                    Relations relation = new Relations();
                    //设置渠道关系
                    relation.setUserId(fromUserName);
                    relation.setServiceId(eventKey.substring(1));
                    //1、判断用户关系是否存在(一级只有渠道，二级用户有上级和渠道)
                    Relations isRelation = relationsMapper.selectRelation(fromUserName);
                    if (Objects.equals(null, isRelation)) {
                        // 1.1 用户关系不存在，用户是一级用户
                        if (relationsMapper.insert(relation) > 0) {
                            respContent = "医美通系统：http://m.superstar.vc";
                        } else {
                            respContent = "用户渠道关系未形成，医美通：http://m.superstar.vc";
                        }
                    } else {
                        // 1.2 用户关系存在，用户是二级用户
                        if (Objects.equals(null, isRelation.getServiceId())) {
                            // 1.2.1 用户渠道关系是否存在
                            // 渠道关系不存在，更新渠道关系
                            if (relationsMapper.updateServiceRelation(relation) > 0) {
                                respContent = "医美通系统：http://m.superstar.vc";
                            } else {
                                respContent = "用户渠道关系未形成，医美通：http://m.superstar.vc";
                            }
                        } else {
                            //1.2.2 渠道关系存在
                            respContent = "医美通系统：http://m.superstar.vc";
                        }
                    }
                }// 订阅事件 或 未关注扫描二维码事件 结束**/
            } else if ("unsubscribe".equals(event)) { // 取消订阅事件
//                    // todo 处理取消订阅事件
            } else if ("SCAN".equals(event)) {
                System.out.println("已关注进入");
                // 已关注扫描二维码事件
                //1、判断二维码来源（1--店家、2--渠道负责人）
                char a = eventKey.charAt(0);
                String first = String.valueOf(a);
                if (Objects.equals("1", first)) {
                    //来源是店家
                    Relations relation = new Relations();//设置上下级关系
                    relation.setUserId(fromUserName);
                    relation.setFatherId(eventKey.substring(9));
                    //1、判断用户关系是否存在
                    Relations isRelation = relationsMapper.selectRelation(fromUserName);
                    if (Objects.equals(null, isRelation)) {
                        //1.1 不存在，就存储
                        if (relationsMapper.insert(relation) > 0) {
                            respContent = "医美通系统：http://m.superstar.vc";
                        } else {
                            respContent = "用户上下级关系未形成，医美通：http://m.superstar.vc";
                        }
                    } else {
                        // 1.2 关系存在，返回医美通网址
                        respContent = "医美通系统：http://m.superstar.vc";
                    }
                } else if (Objects.equals(2, first)) {
                    // 来源是渠道，渠道关系形成
                    Relations relation = new Relations();
                    // 设置渠道关系
                    relation.setUserId(fromUserName);
                    relation.setServiceId(eventKey.substring(1));
                    // 1、判断关系是否存在
                    Relations isRelation = relationsMapper.selectRelation(fromUserName);
                    // 1.1 用户关系不存在，用户是一级用户，存储渠道关系
                    if (Objects.equals(null, isRelation)) {
                        if (relationsMapper.insert(relation) > 0) {
                            respContent = "医美通系统：http://m.superstar.vc";
                        } else {
                            respContent = "渠道关系未形成，医美通系统：http://m.superstar.vc";
                        }
                    } else {
                        // 1.2 用户关系存在，判断渠道关系
                        if (Objects.equals(null, isRelation.getServiceId())) {
                            // 用户为二级用户，渠道关系没有，更新渠道关系
                            if (relationsMapper.updateServiceRelation(relation) > 0) {
                                respContent = "医美通系统：http://m.superstar.vc";
                            } else {
                                respContent = "渠道关系未形成，医美通系统：http://m.superstar.vc";
                            }
                        } else {
                            // 渠道关系存在，返回医美通网址
                            respContent = "医美通系统：http://m.superstar.vc";
                        }
                    }
                }
            } else if ("CLICK".equals(event)) {
                //点击菜单事件
                if (Objects.equals(eventKey, "V1002_MyQRCode")) {
                    //带个人openID的二维码

                }

            }
        }
        //设置文本消息的内容
        textMessage.setContent(respContent);
        //文本消息对象转XML
        respXml = WechatMessageUtil.messageToXml(textMessage);
        return respXml;
    }
}
