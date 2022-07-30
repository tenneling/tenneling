package com.tenneling.service;

import com.tenneling.constant.ResultDataEnum;
import com.tenneling.dao.ToDoListMapper;
import com.tenneling.entity.base.ToDoList;
import com.tenneling.entity.wechat.ResCommonBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ToDoListService {

    @Autowired
    private ToDoListMapper toDoListMapper;

    public List<ToDoList> selectByUserId(String openid) {
        List<ToDoList> doLists = toDoListMapper.selectByUserId(openid);
        log.info("dolists:{}",doLists);
        return doLists;
    }

    public List<ToDoList> updateToDoStatus(Integer id,String openid,String status) {
        ToDoList toDoList = toDoListMapper.selectByPrimaryKey(id);
        toDoList.setStatus(status);
        toDoListMapper.updateByPrimaryKeySelective(toDoList);
        List<ToDoList> doLists = toDoListMapper.selectByUserId(openid);
        log.info("待完成的dolists:{}",doLists);
        return doLists;
    }

    public ResCommonBody insertToDoList(ToDoList toDoList) {
        toDoListMapper.insert(toDoList);
        ResCommonBody resCommonBody = new ResCommonBody();
        resCommonBody.setCode(ResultDataEnum.SUCCESS.getCode());
        resCommonBody.setMsg(ResultDataEnum.SUCCESS.getMsg());
        return resCommonBody;
    }

    public ResCommonBody deleteToDoList(ToDoList toDoList) {
        toDoListMapper.deleteByPrimaryKey(toDoList.getId());
        ResCommonBody resCommonBody = new ResCommonBody();
        resCommonBody.setCode(ResultDataEnum.SUCCESS.getCode());
        resCommonBody.setMsg(ResultDataEnum.SUCCESS.getMsg());
        return resCommonBody;
    }
}
