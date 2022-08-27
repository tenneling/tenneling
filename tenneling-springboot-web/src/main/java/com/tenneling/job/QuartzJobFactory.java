package com.tenneling.job;

import com.tenneling.entity.base.LnJobDetail;
import com.tenneling.utils.DateUtils;
import com.tenneling.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LnJobDetail scheduleJob = (LnJobDetail)context.getMergedJobDataMap().get("scheduleJob");

        try{
            String beginTime = DateUtils.getCurrentTime();
            Job executeJob = (Job) SpringContextUtils.getBean(scheduleJob.getBeanName());
            executeJob.execute(context);
            log.info("正在执行任务：{}，{}",scheduleJob.getJobId(), beginTime);
            String endTime = DateUtils.getCurrentTime();
            log.info("任务执行结束：{}，{}",scheduleJob.getJobId(),endTime);

        } catch (SchedulerException e) {
            log.info("任务执行失败：{}，原因为{}",scheduleJob.getJobId(), e);
        }
    }
}
