package com.tenneling.config;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class WeChartProperties {

    private String appId;

    private String appSecret;

    private String requestUrl;

}

