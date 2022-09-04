package com.tenneling.job;

import com.tenneling.dao.LnJobDetailMapper;
import com.tenneling.entity.base.LnJobDetail;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(value = 1)
@Slf4j
public class QuartzJobListener implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private LnJobDetailMapper lnJobDetailMapper;

    /**
     * 初始启动quartz
     *
     * @param
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            // quartzScheduler.startJob();
            List<LnJobDetail> list = lnJobDetailMapper.selectAllList();

            for (LnJobDetail job : list) {
                try {
                    TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
                    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    //不存在，创建一个
                    if (null == trigger) {
                        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                                .withIdentity(job.getJobName(), job.getJobGroup()).build();
                        jobDetail.getJobDataMap().put("scheduleJob", job);
                        //表达式调度构建器
                        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                                .getCron());
                        //按新的cronExpression表达式构建一个新的trigger
                        trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                        log.info("任务:{},时间表达式:{}",job.getJobId(),job.getCron());
                        scheduler.scheduleJob(jobDetail, trigger);
                    } else {
                        // Trigger已存在，那么更新相应的定时设置
                        //表达式调度构建器
                        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                                .getCron());
                        //按新的cronExpression表达式重新构建trigger
                        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                                .withSchedule(scheduleBuilder).build();
                        log.info("任务:{},时间表达式:{}",job.getJobId(),job.getCron());
                        //按新的trigger重新设置job执行
                        scheduler.rescheduleJob(triggerKey, trigger);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
