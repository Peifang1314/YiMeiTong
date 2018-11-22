package com.juxing.common.util;

import com.juxing.pojo.mysqlPojo.Orders;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/11/12 20
 * @Description: List模仿SQL的group by分组
 * 代码地址：https://blog.csdn.net/evilcry2012/article/details/52708616
 */
public class ListUtil {}

//    public static List groupByList(final List<Object> List) {
//
//        List groupbyList = new ArrayList();
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        for (Orders orders : List) {
//            if (obj.getXxx() != null && obj.getXxx().getXyy() != null) {
//                Xyy xyy = obj.getXxx().getXyy();
//                //org.apache.commons.lang.StringUtils
//                // key值的构成：Xxx编码:Xxx名称,以这两个来进行分组，统计
//                String key = StringUtils.trimToEmpty(xyy.getCode()) + ":"
//                        + StringUtils.trimToEmpty(xyy.getName());
//                if (map.containsKey(key)) {
//                    Integer val = obj.getAmount().intValue() + map.get(key).intValue();
//                    map.put(key, val);
//                } else {
//                    map.put(key, obj.getAmount().intValue());
//                }
//            }
//        }
//
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            String[] names = StringUtils.splitByWholeSeparator(entry.getKey(), ":");
//            Xyy rc = new Xyy();
//            XxxDTO dto = new XxxDTO();
//            rc.setCode(names[0]);
//            rc.setName(names[1]);
//            dto.setXyy(rc);
//            dto.setAmount(Double.valueOf(entry.getValue()));
//            groupbyList.add(dto);
//        }
//        return groupbyList;
//    }

