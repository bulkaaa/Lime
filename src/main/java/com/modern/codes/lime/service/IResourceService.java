package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ResourcePOJO;

import java.util.List;

public interface IResourceService {
    List<ResourcePOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    ResourcePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
}
