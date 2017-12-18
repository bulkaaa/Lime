package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ResourceCategoryPOJO;

import java.util.List;

public interface IResourceCategoryService {
    List<ResourceCategoryPOJO> findAll();
    void delete(String id);
    ResourceCategoryPOJO save(Object t);
    boolean exists(String id);
    boolean exists(Object t);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    ResourceCategoryPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    List<ResourceCategoryPOJO> findByName(String name);
    ResourceCategoryPOJO findByResourcesName(String resourceName);
    ResourceCategoryPOJO findByResourcesId(String resourceId);
}