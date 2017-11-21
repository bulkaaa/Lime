package com.modern.codes.lime.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
@NoRepositoryBean
public abstract class BasicCRUDService<T,ID extends Serializable> implements IBasicCRUDService {
    //methods For all model's classes that does not access strictly databaset
    @Autowired
    SimpleJpaRepository<T, ID> basic;
    @SuppressWarnings("unchecked")
    public List<T> getListOfAll(){ return Lists.newArrayList(basic.findAll()); }
}
