package com.tenneling.config;

import com.tenneling.job.GetWeiXinAccessTokenTask;
import com.tenneling.job.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {
    /**
     * 1、创建Job对象
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factoryBean=new JobDetailFactoryBean();
        //关联我们自己的Job类
        factoryBean.setJobClass(GetWeiXinAccessTokenTask.class);
        return factoryBean;
    }

    /**
     * 2、创建Trigger对象
     * 使用setRepeatInterval
     */
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(@Qualifier("jobDetailFactoryBean") JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean factoryBean=new SimpleTriggerFactoryBean();
        //关联JobDetail对象
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        //该参数表示一个执行的毫秒数
        factoryBean.setRepeatInterval(5000); //每隔5秒执行一次
        //重复次数
        factoryBean.setRepeatCount(5);
        return factoryBean;
    }
    /*
     * 创建Trigger对象
     * 功能描述:
     * 使用cronExpression表达式
     **/
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier("jobDetailFactoryBean") JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean factoryBean=new CronTriggerFactoryBean();
        //关联JobDetail对象
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0 0/30 * * * ?");   //使用cronExpression表达式
        return factoryBean;
    }

    /**
     * 3、创建Scheduler,一个触发器可以执行多个任务
     * 参数1选择需要的bean类型
     * 参数2把自己注入好的JobFactory加入其中就可以访问数据库了
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean simpleTriggerFactoryBean, JobFactory jobFactory){
        SchedulerFactoryBean factoryBean=new SchedulerFactoryBean();
        //关联trigger
        factoryBean.setTriggers(simpleTriggerFactoryBean.getObject());
        factoryBean.setJobFactory(jobFactory);
        return factoryBean;
    }
}
