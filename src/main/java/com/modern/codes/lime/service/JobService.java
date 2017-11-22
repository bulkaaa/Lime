package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IJobDAO;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.pojo.JobPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService extends BasicCRUDService<Job, JobPOJO, IJobDAO> implements IJobService {

    @Autowired
    IJobDAO dao;
    public JobService() {
        super(Job.class, JobPOJO.class);
    }
}