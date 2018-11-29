package com.juxing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juxing.common.util.AdvancedUtil;
import com.juxing.common.util.CommonUtil;
import com.juxing.common.util.DateUtil;
import com.juxing.common.util.WechatMessageUtil;
import com.juxing.mapper.MyTokenMapper;
import com.juxing.mapper.RelationsMapper;
import com.juxing.mapper.UserMapper;
import com.juxing.pojo.mysqlPojo.Relations;
import com.juxing.pojo.mysqlPojo.User;
import com.juxing.pojo.wechatPojo.App;
import com.juxing.pojo.wechatPojo.MyToken;
import com.juxing.pojo.wechatPojo.Ticket;
import com.juxing.pojo.wechatPojo.Token;
import com.juxing.service.WechatCoreService;
import com.juxing.wechat.message.resp.TextMessage;
import org.apache.zookeeper.Op;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/21 17
 * @Description:
 */
@Service
public class WechatCoreServiceImpl implements WechatCoreService {

    @Autowired
    private RelationsMapper relationsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyTokenMapper myTokenMapper;


    @Override
    public String processRequest(HttpServletRequest request) {
        //xml格式的消息数据
        String respXml = null;

        //默认返回的文本消息内容
        String respContent = "巨星集团欢迎你!";

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
                    //来源是店家,该店家和上级店家共用一个渠道id
                    Relations relation = new Relations();
                    String fatherId = eventKey.substring(9);
                    Relations fathRelation = relationsMapper.selectRelation(fatherId);
                    String serviceId = fathRelation.getServiceId();
                    //设置用户关系
                    relation.setUserId(fromUserName);
                    relation.setFatherId(fatherId);
                    relation.setServiceId(serviceId);
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
                    relation.setServiceId(eventKey.substring(9));
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
                    // 来源是店家
                    // 1.店家的关系数据，使用同一个渠道ID
                    Relations fatherRela = relationsMapper.selectRelation(eventKey.substring(1));
                    String serviceId = fatherRela.getServiceId();

                    Relations relation = new Relations();//设置关系数据
                    relation.setUserId(fromUserName);
                    relation.setFatherId(eventKey.substring(1));
                    relation.setServiceId(serviceId);
                    // 2. 判断用户关系是否存在
                    Relations isRelation = relationsMapper.selectRelation(fromUserName);
                    if (Objects.equals(null, isRelation)) {
                        //1.1 不存在，就存储
                        if (relationsMapper.insert(relation) > 0) {
                            respContent = "医美通系统：http://m.superstar.vc";
                        } else {
                            respContent = "用户上下级关系未形成，医美通：http://m.superstar.vc";
                        }
                    } else {
                        // 2.2 关系存在，返回医美通网址
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
//                    fromUserName
                    User user = userMapper.selectByOpenid(fromUserName);
                    if (Objects.equals(null, user)) {
                        // 1 用户未注册医美通
                        respContent = "请先注册医美通：http://m.superstar.vc";
                    } else {
                        //2 已注册，但是个人信息没有推广二维码
                        if (Objects.equals(null, user.getUserShorturl())) {
                            // 2.1 没有带参数二维码短链接，生成一个新的
                            MyToken myToken = myTokenMapper.selectOne();
                            String accessToken = myToken.getAccessToken();

                            TreeMap<String, String> params = new TreeMap();
                            params.put("access_token", accessToken);
                            // output data
                            Map<String, String> intMap = new HashMap();
                            intMap.put("scene_str", fromUserName);
                            Map<String, Map<String, String>> mapMap = new HashMap();
                            mapMap.put("scene", intMap);
                            Map<String, Object> paramsMap = new HashMap();
                            paramsMap.put("action_name", "QR_LIMIT_STR_SCENE");
                            paramsMap.put("action_info", mapMap);
                            String data = JSONObject.toJSONString(paramsMap);

                            Ticket ticket = AdvancedUtil.getTicket(accessToken, data);
                            String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
                            //二维码的图片地址，长链接
                            requestUrl = requestUrl.replace("TICKET", ticket.getTicket());
                            // 长链接转短链接
                            JSONObject jsonObject = CommonUtil.long2short(accessToken, requestUrl);
                            // 短链接地址
                            String shortUrl = jsonObject.getString("short_url");
                            user.setUserShorturl(shortUrl);
                            userMapper.updateUserShorturl(user);
                            respContent = "个人推广二维码：" + shortUrl;
                        } else {
                            //2.2 有短链接
                            String shortUrl = user.getUserShorturl();
                            respContent = "个人推广二维码：" + shortUrl;
                        }
                    }
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
