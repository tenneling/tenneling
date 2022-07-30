package com.tenneling.service;

import com.tenneling.dao.ToDoListMapper;
import com.tenneling.entity.base.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListMapper toDoListMapper;

    public List<ToDoList> selectByUserId(String openid) {
        return toDoListMapper.selectByUserId(openid);
    }
}
