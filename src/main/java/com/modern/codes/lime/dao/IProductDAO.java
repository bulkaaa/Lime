package com.modern.codes.lime.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Product;

/**
 * The interface Product dao.
 */
@Repository
public interface IProductDAO extends IBasicCRUDRepository<Product, String> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findByName(final String name);

    /**
     * Find by added at between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<Product> findByAddedAtBetween(final Date begin, final Date end);

    /**
     * Find by category name list.
     *
     * @param categoryName the category name
     * @return the list
     */
    List<Product> findByCategoryName(final String categoryName);

    /**
     * Find by category id list.
     *
     * @param categoryId the category id
     * @return the list
     */
    List<Product> findByCategoryId(final String categoryId);
}
