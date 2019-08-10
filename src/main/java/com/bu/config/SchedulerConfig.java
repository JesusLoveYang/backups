package com.bu.config;

import com.bu.factory.JobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

@Configuration
public class SchedulerConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JobFactory jobFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("任务已经启动..."+event.getSource());
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setStartupDelay(1);
        return schedulerFactoryBean;
    }

    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
