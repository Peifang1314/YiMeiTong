package com.juxing.service;

import com.juxing.common.vo.R;
import com.juxing.pojo.News;

/**
 * @Auther: Mr.Liu
 * @Date: 2018/10/25 14
 * @Description:
 */
public interface NewsServcie {

    R newsSave(News news);

    R getAllNews();


}
