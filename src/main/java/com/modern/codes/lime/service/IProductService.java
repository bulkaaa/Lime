package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ProductPOJO;

import java.util.List;

public interface IProductService {
    List<ProductPOJO> findAll();
    ProductPOJO delete(String id);
    void save(Object t);
    boolean exists(String id);
}
