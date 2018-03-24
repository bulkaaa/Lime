package com.modern.codes.lime.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Job;

@Repository
public interface IJobDAO extends IBasicCRUDRepository<Job, String> {
    List<Job> findByStartDateBetween(final Date begin, final Date end);

    List<Job> findByEndDateBetween(final Date begin, final Date end);

    List<Job> findByUserId(final String id);

    List<Job> findByProductId(final String id);
}
