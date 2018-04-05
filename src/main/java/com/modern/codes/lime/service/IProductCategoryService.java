package com.modern.codes.lime.service;

import java.util.List;

import com.modern.codes.lime.pojo.ProductCategoryPOJO;

/**
 * The interface Product category service.
 */
public interface IProductCategoryService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<ProductCategoryPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save product category pojo.
     *
     * @param t the t
     * @return the product category pojo
     */
    ProductCategoryPOJO save(Object t);

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean exists(String id);

    /**
     * Exists boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean exists(Object t);

    /**
     * Count long.
     *
     * @return the long
     */
    long count();

    /**
     * Equals boolean.
     *
     * @param t the t
     * @param y the y
     * @return the boolean
     */
    boolean equals(Object t, Object y);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Find by id product category pojo.
     *
     * @param id the id
     * @return the product category pojo
     */
    ProductCategoryPOJO findById(String id);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(Object t);

    /**
     * Save list.
     *
     * @param l the l
     * @return the list
     */
    List<ProductCategoryPOJO> save(List l);

    /**
     * Delete.
     *
     * @param l the l
     */
    void delete(List l);

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<ProductCategoryPOJO> findByName(String name);

    /**
     * Find by products name product category pojo.
     *
     * @param productName the product name
     * @return the product category pojo
     */
    ProductCategoryPOJO findByProductsName(String productName);

    /**
     * Find by products id product category pojo.
     *
     * @param productId the product id
     * @return the product category pojo
     */
    ProductCategoryPOJO findByProductsId(String productId);
}
