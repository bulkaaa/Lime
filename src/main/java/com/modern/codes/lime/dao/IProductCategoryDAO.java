package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.ProductCategory;

@Repository
public interface IProductCategoryDAO extends IBasicCRUDRepository<ProductCategory, String> {
    List<ProductCategory> findByName(final String name);

    ProductCategory findByProductsName(final String productName);

    ProductCategory findByProductsId(final String productId);
}