package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IJobDAO;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.pojo.JobPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService extends BasicCRUDService<Job, JobPOJO, IJobDAO> implements IJobService {

    private IJobDAO dao;
    @Autowired
    public JobService(IJobDAO dao) {
        super(dao, Job.class, JobPOJO.class);
        this.dao = dao;
    }
}