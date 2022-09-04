package com.tenneling.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class RedisKeyExpiration extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisKeyExpiration(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    // 这是继承KeyExpirationEventMessageListener 主动重写的方法，用于处理当监听到了Key失效的时间
    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);
        String messageKey = message.toString();

        // 业务实现
        /*if(messageKey.contains(RedisKey.DELETE_USER)){

            String userKey = redisTemplate.opsForValue().get(messageKey + "_2").toString();
            Long userId = Long.parseLong(userKey);
            // 触发定时任务
            //userService.deleteUserProcess(userId);

            // 删除临时key
            redisTemplate.delete(messageKey + "_2");
        }*/
    }
}
