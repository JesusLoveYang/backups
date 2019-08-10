package com.bu.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class SyncJob implements Job {

    // 加入日志
    private static final Logger logger = LoggerFactory.getLogger(SyncJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = simpleDateFormat.format(date);

        // 获取当前执行的定时任务的细节
        System.out.println(formatDate + " 发工资了！");
    }
}
