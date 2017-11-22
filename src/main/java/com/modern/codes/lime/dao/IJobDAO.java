package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Job;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobDAO extends IBasicCRUDRepository<Job, String>{

}
