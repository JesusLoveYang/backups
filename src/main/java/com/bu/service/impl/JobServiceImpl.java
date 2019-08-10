package com.bu.service.impl;

import com.bu.dao.JobDao;
import com.bu.entity.MyJob;
import com.bu.service.JobService;
import com.bu.util.PageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Override
    public int insertJob(MyJob myJob) {
        return jobDao.insertJob(myJob);
    }

    @Override
    public List<MyJob> getJobList(String username, int pageIndex, int pageSize) {
        int rowIndex = PageCount.pageIndexToRow(pageIndex, pageSize); //获取从哪开始取数据
        List<MyJob> list = jobDao.queryJobList(username, rowIndex, pageSize);
        return list;
    }

    public MyJob getJob(String jobname){
        return jobDao.queryJob(jobname);
    }
}
