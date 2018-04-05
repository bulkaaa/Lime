package com.modern.codes.lime.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface Basic crud repository.
 *
 * @param <T>  the type parameter
 * @param <ID> the type parameter
 */
@NoRepositoryBean
public interface IBasicCRUDRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
    List<T> findAll();
}
