package com.tenneling.job;

import com.tenneling.dao.LnJobDetailMapper;
import com.tenneling.entity.base.LnJobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Order(value = 1)
public class QuartzJobListener implements CommandLineRunner {
    @Autowired
    private QuartzScheduler quartzScheduler;
    @Autowired
    private LnJobDetailMapper lnJobDetailMapper;

    /**
     * 初始启动quartz
     * @param
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            // quartzScheduler.startJob();
            List<LnJobDetail> list = lnJobDetailMapper.selectAllList();

            for (LnJobDetail job : list) {
                try {
                    quartzScheduler.addJob(job.getJobName(),job.getJobName(),job.getJobName(),job.getJobName(), job.getBeanName(),job.getCron(),new HashMap<>(16));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("任务已经启动...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
