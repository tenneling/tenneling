package com.tenneling.job.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tenneling.dao.SysParaMapper;
import com.tenneling.entity.base.SysPara;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component("getGongZhongHaoAccessTokenTask")
@Slf4j
public class GetGongZhongHaoAccessTokenTask implements Job {

    @Value("${wechat.accessTokenUrl}")
    private String requestUrl ;
    @Value("${gongZhongHao.appSecret}")
    private String secret ;
    @Value("${gongZhongHao.appId}")
    private String appid ;
    @Autowired
    private SysParaMapper sysParaMapper;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("获取微信公众号定时AccessToken任务启动");

        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder url = new StringBuilder(requestUrl).append("?appid=").append(appid).append("&secret=").append(secret).append("&grant_type=").append("client_credential");
        //请求接口
        log.info("请求报文：[{}]", url);
        CloseableHttpClient httpclient= HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpget=new HttpGet(url.toString());
        //执行请求,相当于jmeter上点击执行按钮，然后赋值给HttpResponse对象接收
        CloseableHttpResponse httpResponse=httpclient.execute(httpget);
        log.info("响应报文：[{}]", httpResponse);
        //拿到Http响应状态码，例如和200,404,500去比较
        int respinseStatusCode=httpResponse.getStatusLine().getStatusCode();
        log.info("response status code-->"+respinseStatusCode);
        //把响应内容存储在字符串对象
        String responseString= EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson= JSON.parseObject(responseString);
        log.info("respon json from API->"+responseJson);
        String value = String.valueOf(responseJson.get("access_token")) ;
        SysPara sysPara = sysParaMapper.getByKey("GZH_ACCESS_TOKEN");
        sysPara.setParaValue(value);
        sysParaMapper.updateByPrimaryKeySelective(sysPara);
        log.info("更新成功，当前值为{}",value);
    }
}
