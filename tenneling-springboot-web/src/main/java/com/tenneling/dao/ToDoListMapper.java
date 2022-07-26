package com.tenneling.dao;

import com.tenneling.entity.base.ToDoList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDoList record);

    ToDoList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDoList record);

    List<ToDoList> selectByUserId(@Param("openid")String openId, @Param("status")String status);
}