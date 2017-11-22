package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;

import java.util.List;

public interface IRoleService {
    List<RolePOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    RolePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
}
