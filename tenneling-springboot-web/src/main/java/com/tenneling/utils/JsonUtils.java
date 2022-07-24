package com.tenneling.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

public class JsonUtils extends MappingJackson2HttpMessageConverter {
    public JsonUtils() {
        this(Jackson2ObjectMapperBuilder.json().build());
    }

    public JsonUtils(ObjectMapper objectMapper) {
        super(objectMapper);
        // 这里是重点，增加支持的类型，看你的情况加
        // 我这里目前只需要加个 TEXT/PLAIN
        setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN,
                MediaType.APPLICATION_JSON,
                new MediaType("application", "*+json")));
    }
}
