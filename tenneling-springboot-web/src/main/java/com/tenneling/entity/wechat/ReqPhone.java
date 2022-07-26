package com.tenneling.entity.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReqPhone {
    private String access_token;
    private String code;
}
