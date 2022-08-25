package com.tenneling.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(value = 1)
public class QuartzJobListener implements CommandLineRunner {
    @Autowired
    private QuartzScheduler quartzScheduler;

    /**
     * 初始启动quartz
     * @param
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            // quartzScheduler.startJob();

            // 此处就不写获取数据库了，模拟一个集合遍历的数据
            List<Map<String,Object>> listMap=new ArrayList<>();
            Map<String, Object> map1= new HashMap<>(16);
            map1.put("jobClass","com.tenneling.job.impl.GetGongZhongHaoAccessTokenTask");
            map1.put("jobName","job1");
            map1.put("jobGroupName","job1");
            map1.put("jobTime","0 0/30 * * * ?");
            listMap.add(map1);
            Map<String, Object> map2= new HashMap<>(16);
            map2.put("jobClass","com.tenneling.job.impl.GetWeiXinAccessTokenTask");
            map2.put("jobName","job2");
            map2.put("jobGroupName","job2");
            map2.put("jobTime","0 0/30 * * * ?");
            listMap.add(map2);
            for (Map<String, Object> map : listMap) {
                try {
                    quartzScheduler.addJob((String)map.get("jobName"),(String)map.get("jobGroupName"), (String)map.get("jobName"),(String)map.get("jobGroupName"),(String)map1.get("jobClass"),(String)map.get("jobTime"),new HashMap<>(16));
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
