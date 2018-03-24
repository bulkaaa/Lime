package com.modern.codes.lime.service;

import java.util.List;

import com.modern.codes.lime.pojo.ProductCategoryPOJO;

public interface IProductCategoryService {
    List<ProductCategoryPOJO> findAll();

    void delete(String id);

    ProductCategoryPOJO save(Object t);

    boolean exists(String id);

    boolean exists(Object t);

    long count();

    boolean equals(Object t, Object y);

    void deleteAll();

    ProductCategoryPOJO findById(String id);

    void delete(Object t);

    void save(List l);

    void delete(List l);

    List<ProductCategoryPOJO> findByName(String name);

    ProductCategoryPOJO findByProductsName(String productName);

    ProductCategoryPOJO findByProductsId(String productId);
}
