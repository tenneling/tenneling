package com.tenneling;

import com.tenneling.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.tenneling.entity")
@MapperScan(basePackages ="com.tenneling.dao")
@EnableScheduling
@EnableRabbit  //开启基于注解的rabbit模式
public class TennelingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TennelingApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext);
    }


}
