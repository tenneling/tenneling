package com.tenneling.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageData {
    private String msgId;
    private String msgValue;
}
