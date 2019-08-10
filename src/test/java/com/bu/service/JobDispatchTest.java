package com.bu.service;

import com.bu.entity.MyJob;
import com.bu.job.SyncJob;
import com.bu.realm.MyRealm;
import com.bu.service.impl.JobDispatchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobDispatchTest {

    MyJob job = new MyJob();

    @Autowired
    private JobDispatchService jobDispatchService;

    public JobDispatchTest() throws SchedulerException {
    }

    @Test
    public void testJobDispatch() {
        job.setJobName("job1");
        job.setJobGroup("group1");
        job.setCronExpression("0/1 * * * * ?");
        job.setTriggerName("trigger1");
        job.setTriggerGroup("group1");

        jobDispatchService.addQuartzJob(job, SyncJob.class);
        System.out.println("【系统启动】开始(job1 每1秒输出一次)...");
    }
}
