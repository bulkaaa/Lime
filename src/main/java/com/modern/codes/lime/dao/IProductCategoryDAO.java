package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductCategoryDAO extends IBasicCRUDRepository<ProductCategory, String> {
    List<ProductCategory> findByName(final String name);
    ProductCategory findByProductsName(final String productName);
    ProductCategory findByProductsId(final String productId);
}