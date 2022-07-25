package com.tenneling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenneling.entity.wechat.ReqWxUser;
import com.tenneling.entity.wechat.ResWxUser;
import com.tenneling.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("UserController | 用户信息控制器")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "/userLogin")
    @ApiOperation(value="用户登陆", notes="用户登陆", httpMethod = "POST")
    public ResWxUser userLogin(@RequestBody ReqWxUser reqWxUser) throws JsonProcessingException {
        ResWxUser resWxUser =  userService.userLogin(reqWxUser);
        log.info("返回前端：{}",resWxUser);
        return resWxUser;
    }

}
