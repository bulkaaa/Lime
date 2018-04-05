package com.modern.codes.lime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;

/**
 * The interface Formula service.
 */
public interface IFormulaService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<FormulaPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save formula pojo.
     *
     * @param t the t
     * @return the formula pojo
     */
    FormulaPOJO save(Object t);

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
     * Find by id formula pojo.
     *
     * @param id the id
     * @return the formula pojo
     */
    FormulaPOJO findById(String id);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(Object t);

    /**
     * Delete by product id.
     *
     * @param id the id
     */
    void deleteByProductId(String id);

    /**
     * Save list.
     *
     * @param l the l
     * @return the list
     */
    List<FormulaPOJO> save(List l);

    /**
     * Delete.
     *
     * @param l the l
     */
    void delete(List l);

    /**
     * Find by product name list.
     *
     * @param productName the product name
     * @return the list
     */
    List<FormulaPOJO> findByProductName(String productName);

    /**
     * Find by product id list.
     *
     * @param id the id
     * @return the list
     */
    List<FormulaPOJO> findByProductId(String id);

    /**
     * Find by resource id list.
     *
     * @param id the id
     * @return the list
     */
    List<FormulaPOJO> findByResourceId(String id);

    /**
     * Filter by resource list.
     *
     * @param list         the list
     * @param resourceName the resource name
     * @return the list
     */
    static List<FormulaPOJO> filterByResource(final List<FormulaPOJO> list, final String resourceName) {
        return filterByResource(list.stream(), resourceName).collect(Collectors.toList());
    }

    /**
     * Filter by resource stream.
     *
     * @param stream       the stream
     * @param resourceName the resource name
     * @return the stream
     */
    static Stream<FormulaPOJO> filterByResource(final Stream<FormulaPOJO> stream, final String resourceName) {
        return stream.filter(t -> t.getPOJOResource()
                                   .getName()
                                   .equals(resourceName));
    }

    /**
     * Filter by resource list.
     *
     * @param list     the list
     * @param resource the resource
     * @return the list
     */
    static List<FormulaPOJO> filterByResource(final List<FormulaPOJO> list, final ResourcePOJO resource) {
        return filterByResource(list.stream(), resource).collect(Collectors.toList());
    }

    /**
     * Filter by resource stream.
     *
     * @param stream   the stream
     * @param resource the resource
     * @return the stream
     */
    static Stream<FormulaPOJO> filterByResource(final Stream<FormulaPOJO> stream, final ResourcePOJO resource) {
        return stream.filter(t -> t.getPOJOResource()
                                   .equals(resource));
    }

    /**
     * Filter by resources list.
     *
     * @param list      the list
     * @param resources the resources
     * @return the list
     */
    static List<FormulaPOJO> filterByResources(final List<FormulaPOJO> list, final List<String> resources) {
        return filterByResources(list.stream(), resources).collect(Collectors.toList());
    }

    /**
     * Filter by resources stream.
     *
     * @param stream    the stream
     * @param resources the resources
     * @return the stream
     */
    static Stream<FormulaPOJO> filterByResources(final Stream<FormulaPOJO> stream, final List<String> resources) {
        return stream.filter(t -> resources.contains(t.getPOJOResource()
                                                      .getId()));
    }

    /**
     * Filter by product list.
     *
     * @param list        the list
     * @param productName the product name
     * @return the list
     */
    static List<FormulaPOJO> filterByProduct(final List<FormulaPOJO> list, final String productName) {
        return filterByProduct(list.stream(), productName).collect(Collectors.toList());
    }

    /**
     * Filter by product stream.
     *
     * @param stream      the stream
     * @param productName the product name
     * @return the stream
     */
    static Stream<FormulaPOJO> filterByProduct(final Stream<FormulaPOJO> stream, final String productName) {
        return stream.filter(t -> t.getPOJOProduct()
                                   .getName()
                                   .equals(productName));
    }

    /**
     * Filter by product list.
     *
     * @param list    the list
     * @param product the product
     * @return the list
     */
    static List<FormulaPOJO> filterByProduct(final List<FormulaPOJO> list, final ProductPOJO product) {
        return filterByProduct(list.stream(), product).collect(Collectors.toList());
    }

    /**
     * Filter by product stream.
     *
     * @param stream  the stream
     * @param product the product
     * @return the stream
     */
    static Stream<FormulaPOJO> filterByProduct(final Stream<FormulaPOJO> stream, final ProductPOJO product) {
        return stream.filter(t -> t.getPOJOProduct()
                                   .equals(product));
    }
}
