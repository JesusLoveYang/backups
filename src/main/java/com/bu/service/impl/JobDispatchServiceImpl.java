package com.bu.service.impl;

import com.bu.entity.MyJob;
import com.bu.service.JobDispatchService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobDispatchServiceImpl implements JobDispatchService {
    // 添加日志
    private static final Logger logger = LoggerFactory.getLogger(JobDispatchServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public String addQuartzJob(MyJob myJob, Class cls) {
        logger.info("添加定时任务: " + myJob.getJobName() + " 成功");

        try {
            // 注册job
            JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(myJob.getJobName(), myJob.getJobGroup()).build();
            // 创建触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(myJob.getTriggerName(), myJob.getTriggerGroup()).startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(myJob.getCronExpression())).build();

            // 将jobDetail和trigger放到调度器中
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            scheduler.start();
            return "success";
        } catch (SchedulerException e) {
            logger.error("添加定时任务: " + myJob.getJobName() + " 失败，原因：" + e.toString());
            return "error";
        }
    }

    @Override
    public void pauseJob(MyJob myJob) {
        logger.info("暂停定时任务: " + myJob.getJobName() + " 成功");

        try {
            if (null == myJob) {
                logger.info("暂停调度任务参数不正常！");
                return;
            }

            JobKey jobKey = JobKey.jobKey(myJob.getJobName(), myJob.getJobGroup());
            if (jobKey == null) {
                logger.info("任务调度中不存在[" + myJob.getJobName() + "]定时任务，不予进行暂停！");
                return;
            }

            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            logger.error("暂停定时任务: " + myJob.getJobName() + " 异常！" + e.getMessage());
        }
    }

    @Override
    public void resumeJob(MyJob job) {
        logger.info("恢复定时任务: " + job.getJobName() + " 成功");

        try {
            if (job == null) {
                logger.info("恢复调度任务参数不正常！");
                return;
            }

            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            if (jobKey == null) {
                logger.info("任务调度中不存在[" + job.getJobName() + "]定时任务，不予进行恢复！");
                return;
            }

            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            logger.error("恢复任务调度中的定时任务异常！" + e.getMessage());
        }
    }

    @Override
    public String deleteJob(MyJob job) {
        logger.info("删除定时任务 " + job.getJobName() + " 成功");

        try {
            if (null == job) {
                logger.info("删除调度任务参数不正常！");
                return "jobDetail is null";
            }
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            if (null == jobKey) {
                logger.info("任务调度中不存在[" + job.getJobName() + "]定时任务，不予进行删除！");
                return "jobKey is not exists";
            }

            scheduler.deleteJob(jobKey);
            return "success";
        } catch (Exception e) {
            logger.error("删除任务调度中的定时任务异常！" + e.getMessage());
            return "error";
        }
    }
}
