package com.modern.codes.lime.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBasicCRUDRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
    //Place to implement DAO functions for most of models.
}
