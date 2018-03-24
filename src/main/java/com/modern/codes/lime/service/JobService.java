package com.modern.codes.lime.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IJobDAO;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.tools.ParseTools;

@Service
@Transactional
public class JobService extends BasicCRUDService<Job, JobPOJO, IJobDAO> implements IJobService {

    @Autowired
    public JobService(final IJobDAO dao) {
        super(dao, Job.class, JobPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<JobPOJO> findByStartDateBetween(final Date begin, final Date end) {
        return ParseTools.parseList(dao.findByStartDateBetween(begin, end), JobPOJO.class);
    }

    @Override
    public List<JobPOJO> findByEndDateBetween(final Date begin, final Date end) {
        return ParseTools.parseList(dao.findByEndDateBetween(begin, end), JobPOJO.class);
    }

    @Override
    public List<JobPOJO> findByUserId(final String id) {
        return ParseTools.parseList(dao.findByUserId(id), JobPOJO.class);
    }

    @Override
    public List<JobPOJO> findByProductId(final String id) {
        return ParseTools.parseList(dao.findByProductId(id), JobPOJO.class);
    }
    private final IJobDAO dao;
}