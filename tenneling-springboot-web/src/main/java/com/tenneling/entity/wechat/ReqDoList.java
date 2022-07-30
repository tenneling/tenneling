package com.tenneling.entity.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReqDoList {
    private Integer id;
    private String openid;
    private String status;
}
