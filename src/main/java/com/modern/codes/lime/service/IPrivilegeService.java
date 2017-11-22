package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.PrivilegePOJO;

import java.util.List;

public interface IPrivilegeService {
    List<PrivilegePOJO> findAll();
    PrivilegePOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
