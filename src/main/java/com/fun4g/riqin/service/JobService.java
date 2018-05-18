package com.fun4g.riqin.service;

import com.fun4g.riqin.iDao.JobMapper;
import com.fun4g.riqin.model.Job;

/**
 * Created by Administrator on 2016/4/26.
 */

public class JobService {
    public JobMapper getJobMapper() {
        return jobMapper;
    }

    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    private JobMapper jobMapper;

    public boolean addNewJob(Job newJob){
       int i= jobMapper.insert(newJob);
        return i==1?true:false;
    }
}
