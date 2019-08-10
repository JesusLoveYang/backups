package com.bu.service;

import com.bu.entity.MyJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {

    @Autowired
    private JobService jobService;

    String name = "yang";

    @Test
    public void testGetJobList(){
        List<MyJob> list = jobService.getJobList(name, 1,10);
        System.out.println(list.size());
        System.out.println(list.get(0).getJobName());
    }
}
