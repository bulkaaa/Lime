package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.PrivilegePOJO;

import java.util.List;

public interface IPrivilegeService {
    List<PrivilegePOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    PrivilegePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
}
