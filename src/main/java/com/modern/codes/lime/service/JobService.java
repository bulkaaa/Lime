package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IJobDAO;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.pojo.JobPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JobService extends BasicCRUDService<Job, JobPOJO, IJobDAO> implements IJobService {

    private IJobDAO dao;
    @Autowired
    public JobService(IJobDAO dao) {
        super(dao, Job.class, JobPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<JobPOJO> findByStartDateBetween(Date begin, Date end) {
        return ParseTools.parseList(dao.findByStartDateBetween(begin, end), JobPOJO.class);
    }

    @Override
    public List<JobPOJO> findByEndDateBetween(Date begin, Date end) {
        return ParseTools.parseList(dao.findByEndDateBetween(begin, end), JobPOJO.class);

    }

    @Override
    public List<JobPOJO> findByUserId(String id) {
        return ParseTools.parseList(dao.findByUserId(id), JobPOJO.class);
    }
}