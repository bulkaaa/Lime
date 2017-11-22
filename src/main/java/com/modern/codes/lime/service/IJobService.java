package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.JobPOJO;

import java.util.List;

public interface IJobService {
    List<JobPOJO> findAll();
    JobPOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
