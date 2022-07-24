package com.tenneling.entity.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResWxUser {
    private int errcode;
    private String errmsg;
    private String openid;
    private String session_key;
    private String token;
    private String unionid;
}
