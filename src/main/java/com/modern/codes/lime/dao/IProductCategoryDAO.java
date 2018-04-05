package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.ProductCategory;

/**
 * The interface Product category dao.
 */
@Repository
public interface IProductCategoryDAO extends IBasicCRUDRepository<ProductCategory, String> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<ProductCategory> findByName(final String name);

    /**
     * Find by products name product category.
     *
     * @param productName the product name
     * @return the product category
     */
    ProductCategory findByProductsName(final String productName);

    /**
     * Find by products id product category.
     *
     * @param productId the product id
     * @return the product category
     */
    ProductCategory findByProductsId(final String productId);
}