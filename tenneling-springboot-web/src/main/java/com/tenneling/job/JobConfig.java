package com.tenneling.job;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class JobConfig {
    @Autowired
    private JobFactory jobFactory;

    public JobConfig(JobFactory jobFactory){
        this.jobFactory = jobFactory;
    }

    /**
     * 配置SchedulerFactoryBean
     * 将一个方法产生为Bean并交给Spring容器管理
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        // Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 设置自定义Job Factory，用于Spring管理Job bean
        factory.setJobFactory(jobFactory);
        return factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
