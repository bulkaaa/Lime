package com.modern.codes.lime.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface IBasicCRUDRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
      List<T> findAll();
}
