package com.modern.codes.lime.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class JobCustomDAO implements IJobCustomDAO {
    @PersistenceContext
    private EntityManager entityManager;
}