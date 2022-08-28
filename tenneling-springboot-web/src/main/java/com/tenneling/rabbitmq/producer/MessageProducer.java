package com.tenneling.rabbitmq.producer;

import com.tenneling.rabbitmq.MessageData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    void sendTopicMsgs(MessageData data) {
        rabbitTemplate.convertAndSend("exchange.topic",data.getMsgId(),data.getMsgValue());
    }
}
