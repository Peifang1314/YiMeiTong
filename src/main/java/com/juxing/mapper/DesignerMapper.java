package com.juxing.mapper;

import com.juxing.pojo.mysqlPojo.Designer;

import java.util.List;

public interface DesignerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Designer record);

    /**
     *
     * @return 所有的设计师
     */
    List<Designer> selectAllDesigners();

    Designer selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Designer record);
}