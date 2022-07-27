package com.tenneling.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenneling.constant.ResultDataEnum;
import com.tenneling.dao.SysParaMapper;
import com.tenneling.dao.WxUserMapper;
import com.tenneling.entity.base.SysPara;
import com.tenneling.entity.base.WxUser;
import com.tenneling.entity.wechat.*;
import com.tenneling.utils.WeChartUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WxUserMapper wxUserMapper;
    @Autowired
    private SysParaMapper sysParaMapper;

    @Value("${wechat.requestUrl}")
    private String requestUrl ;
    @Value("${wechat.appSecret}")
    private String secret ;
    @Value("${wechat.appId}")
    private String appid ;
    @Value("${wechat.phoneUrl}")
    private String phoneUrl ;
    @Value("${wechat.accessTokenUrl}")
    private String accessTokenUrl ;

    public ResWxUser userLogin(ReqWxUser reqWxUser) throws JsonProcessingException {
        log.info("前端入参：{}",reqWxUser);
        //请求小程序登录接口
        ResWxUser resWxUser = this.requestLogin(reqWxUser);
        if(!(ResultDataEnum.APPID_MISSING.getCode()==(resWxUser.getErrcode())||
                ResultDataEnum.APPID_ERROR.getCode()==(resWxUser.getErrcode())||
                    ResultDataEnum.CODE_ERROR.getCode()==(resWxUser.getErrcode()))){
            //处理用户数据
            this.dealWithUser(reqWxUser,resWxUser);
            //放置token
            resWxUser.setToken(WeChartUtils.getToken(resWxUser.getOpenid()));
        }
        return resWxUser;
    }

    private void dealWithUser(ReqWxUser reqWxUser, ResWxUser resWxUser) throws JsonProcessingException {
        WxUser wxUser = wxUserMapper.getWxUserByOpenId(resWxUser.getOpenid());
        if (wxUser == null){
            wxUser = new WxUser();
            wxUser.setOpenid(resWxUser.getOpenid());
            wxUserMapper.insert(wxUser);
        }
        RawData rawData = JSON.parseObject(reqWxUser.getRawData(), RawData.class);
        wxUser.setNickname(rawData.getNickName());
        wxUser.setAvatarurl(rawData.getAvatarUrl());
        wxUser.setGender(rawData.getGender());
        wxUser.setCity(rawData.getCity());
        wxUser.setCountry(rawData.getCountry());
        wxUser.setProvince(rawData.getProvince());
        JSONObject encryptedData = WeChartUtils.getEncryptedData(reqWxUser.getEncryptedData(), resWxUser.getSession_key(), reqWxUser.getIv());
        if (encryptedData != null){
            String unionId = encryptedData.getString("unionId");
            wxUser.setUnionid(unionId);
        }
        log.info("用户登录信息：{}",wxUser);
        wxUserMapper.updateByPrimaryKeySelective(wxUser);
    }

    private ResWxUser requestLogin(ReqWxUser reqWxUser) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder url = new StringBuilder(requestUrl).append("?appid=").append(appid).append("&secret=").append(secret).append("&js_code=").append(reqWxUser.getCode()).append("&grant_type=").append("authorization_code");
        //请求接口
        HttpEntity<JSONObject> request = new HttpEntity(reqWxUser,headers);
        log.info("url:[{}],请求报文：[{}]", url , JSONObject.toJSONString(request));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url.toString() ,request, String.class);
        log.info("响应报文：[{}]", response.getBody());
        return JSONObject.parseObject(String.valueOf(response.getBody()),ResWxUser.class) ;
    }

    public ResCommonBody getPhone(String codeId) throws JsonProcessingException {
        //从配置表获取accessToken
        SysPara sysPara = sysParaMapper.getByKey("ACCESS_TOKEN");
        log.info("sysPara:{},{}",sysPara,sysPara.getParaValue());
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json");
        StringBuilder url = new StringBuilder(phoneUrl).append("?access_token=").append(sysPara.getParaValue());
        ReqPhone reqPhone = new ReqPhone();
        reqPhone.setCode(codeId);
        //请求接口
        HttpEntity<JSONObject> request = new HttpEntity(reqPhone,headers);
        log.info("url:[{}],请求报文：[{}]", url.toString() , JSONObject.toJSONString(request));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url.toString(),request, String.class);
        log.info("响应报文：[{}]", response.getBody());
        String phone = JSONObject.parseObject(String.valueOf(response.getBody()),String.class);
        ResCommonBody resCommonBody = new ResCommonBody();
        resCommonBody.setMsg("");
        resCommonBody.setCode("");
        return resCommonBody ;
    }

}
