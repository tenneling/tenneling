package com.tenneling.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component("JobFactory")
public class JobFactory extends AdaptableJobFactory {
    //AutowireCapableBeanFactory可以将一个对象添加到Spring IOC容器中，并且完成该对象注入
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    //该方法将实例化的任务对象手动的添加到SpringIOC容器中并且完成对象的注入
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object object = super.createJobInstance(bundle);
        //将object对象添加到Spring IOC容器中并完成注入
        this.autowireCapableBeanFactory.autowireBean(object);
        return object;
    }
}
