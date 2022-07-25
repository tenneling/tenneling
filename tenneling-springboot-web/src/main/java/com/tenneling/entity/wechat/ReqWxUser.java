package com.tenneling.entity.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReqWxUser {

    private String appid;
    private String secret;
    private String code;
    private String grant_type;
    private String iv;
    private String rawData;
    private String signature;
    private String encryptedData;
}
