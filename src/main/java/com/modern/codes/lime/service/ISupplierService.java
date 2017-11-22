package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.SupplierPOJO;

import java.util.List;

public interface ISupplierService {
    List<SupplierPOJO> findAll();
    SupplierPOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
