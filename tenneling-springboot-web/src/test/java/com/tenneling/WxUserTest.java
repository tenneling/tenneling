package com.tenneling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenneling.dao.WxUserMapper;
import com.tenneling.entity.wechat.ReqWxUser;
import com.tenneling.entity.base.WxUser;
import com.tenneling.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class WxUserTest {

    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private UserService userService;

    @Test
    public void test01(){
        WxUser wxUser = new WxUser();
        wxUser.setAvatarurl("wew");
        log.info("sds :{}",wxUser);
        wxUserMapper.insert(wxUser);
    }

    @Test
    public void test02() throws JsonProcessingException {
        ReqWxUser reqWxUser = new ReqWxUser();
        reqWxUser.setEncryptedData("wewew");
        reqWxUser.setIv("23232");
        userService.userLogin(reqWxUser);
    }
}
