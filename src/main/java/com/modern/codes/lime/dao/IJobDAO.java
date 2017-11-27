package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IJobDAO extends IBasicCRUDRepository<Job, String> {
    List<Job> findByStartDateBetween(Date begin, Date end);
    List<Job> findByEndDateBetween(Date begin, Date end);
    List<Job> findByUser(User user);
}
