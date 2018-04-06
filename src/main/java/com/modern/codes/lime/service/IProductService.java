package com.modern.codes.lime.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.pojo.ProductPOJO;

/**
 * The interface Product service.
 */
public interface IProductService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<ProductPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save product pojo.
     *
     * @param t the t
     * @return the product pojo
     */
    ProductPOJO save(Object t);

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
     * Find by id product pojo.
     *
     * @param id the id
     * @return the product pojo
     */
    ProductPOJO findById(String id);

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
    List<ProductPOJO> save(List l);

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
    List<ProductPOJO> findByName(String name);

    /**
     * Find by added at between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<ProductPOJO> findByAddedAtBetween(Date begin, Date end);

    /**
     * Find by category name list.
     *
     * @param categoryName the category name
     * @return the list
     */
    List<ProductPOJO> findByCategoryName(String categoryName);

    /**
     * Find by category id list.
     *
     * @param categoryId the category id
     * @return the list
     */
    List<ProductPOJO> findByCategoryId(String categoryId);

    /**
     * Filter by name list.
     *
     * @param list the list
     * @param name the name
     * @return the list
     */
    static List<ProductPOJO> filterByName(final List<ProductPOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    /**
     * Filter by name stream.
     *
     * @param stream the stream
     * @param name   the name
     * @return the stream
     */
    static Stream<ProductPOJO> filterByName(final Stream<ProductPOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    /**
     * Filter by category list.
     *
     * @param list     the list
     * @param category the category
     * @return the list
     */
    static List<ProductPOJO> filterByCategory(final List<ProductPOJO> list, final ProductCategory category) {
        return filterByCategory(list.stream(), category).collect(Collectors.toList());
    }

    /**
     * Filter by category stream.
     *
     * @param stream   the stream
     * @param category the category
     * @return the stream
     */
    static Stream<ProductPOJO> filterByCategory(final Stream<ProductPOJO> stream, final ProductCategory category) {
        return stream.filter(t -> t.getCategory()
                                   .equals(category));
    }
}
