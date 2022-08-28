package com.tenneling.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class MessageConsumer {
    @RabbitListener
    public void  receiveMessage(Message message){ //千万注意这里的Message是 org.springframework.amqp.core.Message 别导错包了
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
