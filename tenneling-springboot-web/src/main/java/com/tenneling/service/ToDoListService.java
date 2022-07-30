package com.tenneling.service;

import com.tenneling.constant.ResultDataEnum;
import com.tenneling.dao.ToDoListMapper;
import com.tenneling.entity.base.ToDoList;
import com.tenneling.entity.wechat.ResCommonBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ToDoListService {

    @Autowired
    private ToDoListMapper toDoListMapper;

    public List<ToDoList> selectByUserId(@Param("openid") String openid, @Param("status")String status) {
        List<ToDoList> doLists = toDoListMapper.selectByUserId(openid,status);
        log.info("dolists:{}",doLists);
        return doLists;
    }

    public ResCommonBody updateToDoStatus(Integer id,String openid,String status) {
        ToDoList toDoList = toDoListMapper.selectByPrimaryKey(id);
        toDoList.setStatus(status);
        toDoListMapper.updateByPrimaryKeySelective(toDoList);

        ResCommonBody resCommonBody = new ResCommonBody();
        resCommonBody.setCode(ResultDataEnum.SUCCESS.getCode());
        resCommonBody.setMsg(ResultDataEnum.SUCCESS.getMsg());
        return resCommonBody;
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
