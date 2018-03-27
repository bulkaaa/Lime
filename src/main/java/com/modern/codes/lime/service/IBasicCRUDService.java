package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.modern.codes.lime.dao.IBasicCRUDRepository;

@NoRepositoryBean
public interface IBasicCRUDService<T, T_POJO, T_DAO extends IBasicCRUDRepository<T, String>> {
    List<T_POJO> findAll();

    void delete(String id);

    T_POJO save(T_POJO t);

    boolean exists(String id);

    boolean exists(T_POJO t);

    long count();

    boolean equals(T_POJO t, T_POJO y);

    void deleteAll();

    T_POJO findById(String id);

    void delete(T_POJO t);

    List<T_POJO> save(List<T_POJO> l);

    void delete(List<T_POJO> l);
}
