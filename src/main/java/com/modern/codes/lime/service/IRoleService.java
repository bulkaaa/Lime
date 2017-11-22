package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;

import java.util.List;

public interface IRoleService {
    List<RolePOJO> findAll();
    RolePOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
