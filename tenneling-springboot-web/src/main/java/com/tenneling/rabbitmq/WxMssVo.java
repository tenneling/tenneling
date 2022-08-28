package com.tenneling.rabbitmq;

import java.util.Map;

public class WxMssVo {
    private String touser;//用户openid

    private String template_id;//订阅消息模版id

    private String page = "pages/index/index";//默认跳到小程序首页

    private Map data;//推送文字
}
