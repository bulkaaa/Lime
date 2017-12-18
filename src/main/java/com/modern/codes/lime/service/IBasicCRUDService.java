package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IBasicCRUDRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
@NoRepositoryBean
public interface IBasicCRUDService <T, T_POJO,  T_DAO extends IBasicCRUDRepository<T, String>> {
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
    void save(List<T_POJO> l);
    void delete(List<T_POJO> l);
}
