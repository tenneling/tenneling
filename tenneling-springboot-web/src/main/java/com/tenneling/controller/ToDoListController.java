package com.tenneling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenneling.service.ToDoListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api("ToDoListController | 待办列表控制器")
@RestController
@Slf4j
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    @ResponseBody
    @GetMapping(value = "/getToDoList")
    @ApiOperation(value="获取待办列表", notes="获取待办列表", httpMethod = "GET")
    public void getToDoList(String openid) throws JsonProcessingException {
        log.info("返回前端：{}",openid);
    }
}