package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ResourcePOJO;

import java.util.List;

public interface IResourceService {
    List<ResourcePOJO> findAll();
    ResourcePOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
