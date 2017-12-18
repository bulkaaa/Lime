package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductCategoryDAO extends IBasicCRUDRepository<ProductCategory, String> {
    List<ProductCategory> findByName(String name);
    ProductCategory findByProductsName(String productName);
    ProductCategory findByProductsId(String productId);
}