package com.tenneling.controller;

import com.tenneling.entity.base.ToDoList;
import com.tenneling.entity.wechat.ReqDoList;
import com.tenneling.entity.wechat.ResCommonBody;
import com.tenneling.service.ToDoListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("ToDoListController | 待办列表控制器")
@RestController
@Slf4j
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    @ResponseBody
    @GetMapping(value = "/getToDoList")
    @ApiOperation(value="获取待办列表", notes="获取待办列表", httpMethod = "GET")
    public List<ToDoList> getToDoList(String openid,String status){
        log.info("入参userId：{},status :{}",openid,status);
        return toDoListService.selectByUserId(openid,status);
    }

    @ResponseBody
    @PostMapping(value = "/updateToDoStatus")
    @ApiOperation(value="更新待办列表", notes="更新待办列表", httpMethod = "POST")
    public ResCommonBody updateToDoStatus(@RequestBody ReqDoList reqDoList){
        log.info("更新待办列表：{}",reqDoList);
        return toDoListService.updateToDoStatus(reqDoList.getId(),reqDoList.getOpenid(),reqDoList.getStatus());
    }

    @ResponseBody
    @PostMapping(value = "/insertToDoList")
    @ApiOperation(value="新增待办列表", notes="新增待办列表", httpMethod = "POST")
    public ResCommonBody insertToDoList(@RequestBody ToDoList toDoList){
        log.info("新增待办列表：{}",toDoList);
        return toDoListService.insertToDoList(toDoList);
    }

    @ResponseBody
    @PostMapping(value = "/deleteToDoList")
    @ApiOperation(value="删除待办列表", notes="删除待办列表", httpMethod = "POST")
    public ResCommonBody deleteToDoList(@RequestBody ToDoList toDoList){
        log.info("删除待办列表：{}",toDoList);
        return toDoListService.deleteToDoList(toDoList);
    }
}
