package com.tenneling.entity.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReqWxUser {
    private String openid;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String city;
    private String country;
    private String language;
    private String province;
}
