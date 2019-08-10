package com.bu.dao;

import com.bu.entity.MyJob;
import com.bu.job.SyncJob;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobDaoTest {

    @Autowired
    private JobDao jobDao;

    String username = "yang";

    @Test
    @Ignore
    public void testAQueryJobList(){
        List<MyJob> list = jobDao.queryJobList(username, 0, 10);
        System.out.println(list.size());
        System.out.println(list.get(0).getCronExpression());

        MyJob myJob = jobDao.queryJob("job1");
        System.out.println(myJob.getTriggerName());
    }

    @Test
    public void testBInsertJob(){
        MyJob job = new MyJob();
        job.setJobName("job3");
        job.setJobGroup("group3");
        job.setCronExpression("0/2 * * * * ?");
        job.setTriggerName("trigger3");
        job.setTriggerGroup("group3");
        job.setUserId(5L);

        int effectNum = jobDao.insertJob(job);
        System.out.println(effectNum);
    }
}
