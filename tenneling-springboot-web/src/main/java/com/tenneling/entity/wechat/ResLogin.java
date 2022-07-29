package com.tenneling.entity.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResLogin {
    private int errcode;
    private String errmsg;
    private String session_key;
    private String openid;
    private String token;
}
